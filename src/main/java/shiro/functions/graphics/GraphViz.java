/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.functions.graphics;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.algorithms.layout.util.VisRunner;
import edu.uci.ics.jung.algorithms.util.IterativeContext;
import edu.uci.ics.jung.graph.Graph;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

/**
 *
 * @author jeffreyguenther
 */
public class GraphViz<V, E> extends Region {

    private Relaxer relaxer;
    private Layout<V, E> layout;
    private double CIRCLE_SIZE = 25;
    private Map<V, Circle> vertices;
    private Map<E, Line> edges;
    private boolean notRendered;

    public GraphViz(Layout<V, E> layout) {
        this.layout = layout;
        vertices = new HashMap<>();
        edges = new HashMap<>();
        notRendered = true;
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);

        layout.setSize(new Dimension((int) width, (int) height));
        layout.reset();
        render();

    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        layout.reset();
        render();
    }

    private void render() {
        // relax the layout

        if (relaxer != null) {
            relaxer.stop();
            relaxer = null;
        }
        if (layout instanceof IterativeContext) {
            layout.initialize();
            if (relaxer == null) {
                relaxer = new VisRunner((IterativeContext) this.layout);
                relaxer.prerelax();
                relaxer.relax();
            }
        }

        Graph<V, E> graph = layout.getGraph();

        // draw the edges
        for (E e : graph.getEdges()) {
            // get the end points of the edge
            edu.uci.ics.jung.graph.util.Pair<V> endpoints = graph.getEndpoints(e);

            // Get the end points as Point2D objects so we can use them in the 
            // builder
            java.awt.geom.Point2D pStart = layout.transform(endpoints.getFirst());
            java.awt.geom.Point2D pEnd = layout.transform(endpoints.getSecond());

            if (notRendered) {
                // Draw the line
                Line line = LineBuilder.create()
                        .startX(pStart.getX())
                        .startY(pStart.getY())
                        .endX(pEnd.getX())
                        .endY(pEnd.getY())
                        .build();
                // add the edges to the screen
                edges.put(e, line);
                this.getChildren().add(line);
            } else {
                Line l = edges.get(e);
                l.setStartX(pStart.getX());
                l.setStartY(pStart.getY());
                l.setEndX(pEnd.getX());
                l.setEndY(pEnd.getY());
            }
        }

        // draw the vertices in the graph
        for (V v : graph.getVertices()) {
            // Get the position of the vertex
            java.awt.geom.Point2D p = layout.transform(v);

            if (notRendered) {

                // draw the vertex as a circle
                Circle circle = CircleBuilder.create()
                        .centerX(p.getX())
                        .centerY(p.getY())
                        .radius(CIRCLE_SIZE)
                        .build();
                vertices.put(v, circle);
                // add it to the group, so it is shown on screen
                this.getChildren().add(circle);

                // add labels
                Label t = new Label(v.toString());
                t.setTextFill(Color.WHITE);
                this.getChildren().add(t);
                t.layoutXProperty().bind(circle.centerXProperty().subtract(t.widthProperty().divide(2)));
                t.layoutYProperty().bind(circle.centerYProperty().subtract(t.widthProperty().divide(2)));

            } else {
                Circle c = vertices.get(v);
                c.setCenterX(p.getX());
                c.setCenterY(p.getY());
            }
        }
        notRendered = false;
    }
}
