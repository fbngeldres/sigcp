package com.dbaccess.cellmanager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.TopologicalOrderIterator;

public class CellManager {
	private CellDirectedMultigraph cellGraph;
	private RowSources sources;

	public CellManager(RowSources sources) {
		this.sources = sources;
		rebuildGraphAndDependencies();
	}

	public void rebuildGraphAndDependencies() {
		cellGraph = new CellDirectedMultigraph();
		addAllVertexCells();
		refreshAllDependencies();
	}

	/**
	 */
	public void addAllVertexCells() {
		for (Map.Entry<String, List<Row>> source : getSources().entrySet()) {
			for (Row row : source.getValue()) {
				addAllRowCells(row);
			}
		}
	}

	private void addAllRowCells(Row row) {
		for (Cell cell : row.getCells()) {
			cellGraph.addVertex(cell);
		}
	}

	private void removeAllRowCells(Row row) {
		for (Cell cell : row.getCells()) {
			cellGraph.removeVertex(cell);
		}
	}

	/**
	 * Para todas las celdas de todas las filas de todos los sources, recalcular
	 * sus dependencias.
	 */
	private void refreshAllDependencies() {
		for (Map.Entry<String, List<Row>> source : getSources().entrySet()) {
			for (Row row : source.getValue()) {
				for (Cell cell : row.getCells()) {
					refreshCellDependencies(cell);
				}
			}
		}
	}

	public void refreshCellDependencies(Cell cell) {
		if (!cell.isFormula()) {
			return;
		}
		CellGroups cellGroups = cell.refreshCellGroups(getSources());

		// Fue necesario el addAll intermedio para evitar un
		// concurrentmodificationexception
		Set<DefaultEdge> incoming = new HashSet<DefaultEdge>();
		incoming.addAll(cellGraph.incomingEdgesOf(cell));
		cellGraph.removeAllEdges(incoming);

		Cell incomingCellDebug = null;

		try {
			for (Map.Entry<String, List<Cell>> entry : cellGroups.entrySet()) {
				for (Cell incomingCell : entry.getValue()) {
					incomingCellDebug = incomingCell;
					cellGraph.addEdge(incomingCell, cell);
				}
			}
		} catch (IllegalArgumentException e) {
			// Este error normalmente pasa cuando se hace putCell, se referencia
			// a esa celda,
			// y luego se sobreescribe la celda con otro putCell, dejandola
			// asociada
			// pero huerfana.
			throw new IllegalArgumentException("could not add edges to cell " + cell + " probable problem with cell "
					+ incomingCellDebug.toString(), e);
		}
	}

	public void recalcAll() {
		System.out.println("topo started");
		TopologicalOrderIterator<Cell, DefaultEdge> topo = new TopologicalOrderIterator<Cell, DefaultEdge>(cellGraph);
		while (topo.hasNext()) {
			Cell cell = topo.next();

			if (cell.isFormula()) {
				cell.recalc();
				// System.out.println("  recalc " + cell.toString());
			} else {
				// System.out.println("  visited " + cell.toString());
			}
		}
		System.out.println("topo ended");
		afterMassRecalc();
	}

	public void recalcNullOnly() {
		// System.out.println("topo nullonly started");
		TopologicalOrderIterator<Cell, DefaultEdge> topo = new TopologicalOrderIterator<Cell, DefaultEdge>(cellGraph);
		while (topo.hasNext()) {
			Cell cell = topo.next();

			if (cell.isFormula() && cell.getValue() == null) {
				cell.recalc();
				// System.out.println("  recalc " + cell.toString());
			} else {
				// System.out.println("  visited " + cell.toString());
			}
		}
		// System.out.println("topo nullonly ended");
		afterMassRecalc();
	}

	private void recalcAndPropagate(Cell startCell) {
		BreadthFirstIterator<Cell, DefaultEdge> bfs = new BreadthFirstIterator<Cell, DefaultEdge>(cellGraph, startCell);
		System.out.println("bfs started");
		while (bfs.hasNext()) {
			Cell cell = bfs.next();
			if (cell.isFormula()) {
				cell.recalc();
				// System.out.println("  recalc: " + cell.toString());
			} else {
				// System.out.println("  visited: " + cell.toString());
			}
		}
		System.out.println("bfs ended");
		afterMassRecalc();
	}

	public void afterMassRecalc() {
	}

	public void insertRow(String sourceName, Row row) {
		// insertar el row
		getSources().get(sourceName).add(row);

		// reconstruir grafo y dependencias
		rebuildGraphAndDependencies();

		// recalcular todo
		recalcAll();
	}

	public void deleteRow(String sourceName, Row row) {
		// quitar el row
		getSources().get(sourceName).remove(row);

		// reconstruir grafo y dependencias
		rebuildGraphAndDependencies();

		// recalcular todo
		recalcAll();
	}

	// TODO: completar. es una optimizacion de insertRow total
	// esta version no sirve si row tiene celdas con formulas
	public void insertRowIncremental(String sourceName, Row row) {
		// insertar el row
		getSources().get(sourceName).add(row);
		// agregar celdas del row como vertices del grafo
		addAllRowCells(row);

		// Conseguir Set de celdas potencialmente dependientes del nuevo row
		// Una celda es potencialmente dependiente si entre sus cell selectors
		// hay al menos uno que hace referencia al rowsource del nuevo row.
		Set<Cell> potencialmenteDependientes = new HashSet<Cell>();
		for (Cell cell : cellGraph.vertexSet()) {
			CellSelectorGroup selGroup = cell.getSelectorGroup();
			if (!cell.isFormula()) {
				// saltar celdas no-formula
				continue;
			}
			for (Map.Entry<String, CellSelector> entry : selGroup.entrySet()) {
				if (sourceName.equals(entry.getValue().getRowSourceName())) {
					potencialmenteDependientes.add(cell);
					break;
				}
			}
		}

		// Hacer refresh de las dependencias de las potencialmente dependientes
		for (Cell cell : potencialmenteDependientes) {
			refreshCellDependencies(cell);
		}

		// Recalcular las celdas potencialmente dependientes y propagar sus
		// cambios
		for (Cell cell : potencialmenteDependientes) {
			recalcAndPropagate(cell);
		}
	}

	public void setCellValue(Cell cell, Object value) {
		cell.setValue(value);
		recalcAndPropagate(cell);
	}

	public String asGraphViz() {
		final String DBLQUOTE = "\"";
		final String INDENT = "  ";
		StringBuffer buffer = new StringBuffer();

		Map<Cell, String> ids = new HashMap<Cell, String>();

		buffer.append("digraph G {\n");
		buffer.append(INDENT + "node [shape=record];\n");

		// agregar vertices en un cluster por cada source
		int clusterNum = 0;
		for (Map.Entry<String, List<Row>> source : getSources().entrySet()) {
			buffer.append(INDENT + "subgraph cluster" + clusterNum + " {\n");
			buffer.append(INDENT + INDENT + "label = " + DBLQUOTE + source.getKey() + DBLQUOTE + "\n");

			int rowNum = 0;
			for (Row row : source.getValue()) {
				for (String colName : row.getColNames()) {
					Cell cell = row.getCell(colName);
					// "c" + clusterNum +
					String id = "[" + rowNum + "]." + colName;
					ids.put(cell, id);
					buffer.append(INDENT + INDENT + DBLQUOTE + id + DBLQUOTE + "\n");
				}
				rowNum++;
			}
			buffer.append(INDENT + "}\n");
			clusterNum++;
		}

		Set<DefaultEdge> edges = cellGraph.edgeSet();

		for (DefaultEdge defaultEdge : edges) {
			buffer.append(INDENT + DBLQUOTE + ids.get(defaultEdge.getSource()) + DBLQUOTE + " -> " + DBLQUOTE
					+ ids.get(defaultEdge.getTarget()) + DBLQUOTE + ";\n");
		}

		buffer.append("}\n");

		return buffer.toString();
	}

	public void updateSourceHack(String sourceName, List<Row> source) {
		// TODO: hacer esto confiable acompa#ado de un rebuildAll
		getSources().put(sourceName, source);
	}

	public RowSources getSources() {
		return sources;
	}
}
