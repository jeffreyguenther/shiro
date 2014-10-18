/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.shirolang.playground;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.fxmisc.richtext.*;
import org.reactfx.EventStream;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;

import java.net.URL;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class ShiroPlayground extends Application {
    private CodeArea codeArea;
    private ExecutorService executor;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("fxml_viewer.fxml");
        FXMLLoader loader = new FXMLLoader();
        BorderPane root = (BorderPane) loader.load(resource);
        final FXMLViewerController c = loader.getController();

        executor = Executors.newSingleThreadExecutor();
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        EventStream<PlainTextChange> textChanges = codeArea.plainTextChanges();

        textChanges.successionEnds(Duration.ofMillis(500))
            .supplyTask(this::computeHighlightingAsync)
            .awaitLatest(textChanges)
            .subscribe(this::applyHighlighting);

        codeArea.replaceText("port a Double(343)\n" +
                "port b String(\"dfd\")\n");
        new StackPane(codeArea);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("syntax.css").toExternalForm());
        primaryStage.setTitle("Shiro Playground");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // stop the thread service
        executor.shutdown();
    }

    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync(){
        String code = codeArea.getText();

        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(code);
            }
        };
        executor.execute(task);
        return task;
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text){
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        int lastEnd = 0;

        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(text));
        // parse

        for(Token t: lex.getAllTokens()){
            spansBuilder.add(Collections.emptyList(), t.getStartIndex() - lastEnd);
            spansBuilder.add(Collections.singleton(getStyleClass(t)), (t.getStopIndex() + 1 - t.getStartIndex()));
            lastEnd = t.getStopIndex() + 1;
        }

        spansBuilder.add(Collections.emptyList(), text.length() - lastEnd);

        return spansBuilder.create();
    }

    private static String getStyleClass(Token t){
        switch (t.getType()){
            case ShiroLexer.BEGIN:
                return "begin-end";
            case ShiroLexer.END:
                return "begin-end";
            case ShiroLexer.MFNAME:
                return "mf";
            case ShiroLexer.IDENT:
                return "ident";
            default:
                return "";
        }
    }

    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        codeArea.setStyleSpans(0, highlighting);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
