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
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.fxmisc.richtext.*;
import org.reactfx.EventStream;
import org.reactfx.util.Try;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class ShiroPlaygroundParser extends Application {
    private CodeArea codeArea;
    private ExecutorService executor;


    @Override
    public void start(Stage primaryStage) throws Exception {
        executor = Executors.newSingleThreadExecutor();
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        EventStream<PlainTextChange> textChanges = codeArea.plainTextChanges();

        textChanges.successionEnds(Duration.ofMillis(500))
            .supplyTask(this::computeHighlightingAsync)
            .awaitLatest(textChanges)
            .subscribe(this::applyHighlighting);

        codeArea.replaceText("port a Double(343)\n" +
                "port b String(\"dfd\")\n" +
                "node D begin\n" +
                "port a Double(12)\n" +
                "end");

        Scene scene = new Scene(new StackPane(codeArea), 600, 400);
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
        if(text.length() > 0){
            ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(text));
            lex.getErrorListeners().clear();
            // parse
            ShiroParser parser = new ShiroParser(new CommonTokenStream(lex));
            parser.getErrorListeners().clear();
            parser.setBuildParseTree(true);
            ShiroParser.ShiroContext shiro = parser.shiro();

            ParseTreeWalker walker = new ParseTreeWalker();
            SyntaxHighlighter h = new SyntaxHighlighter(text.length());
            walker.walk(h, shiro);
            return h.getStyles();
        }else{
            StyleSpansBuilder<Collection<String>> objectStyleSpansBuilder = new StyleSpansBuilder<>();
            objectStyleSpansBuilder.add(Collections.emptyList(), 0);
            return objectStyleSpansBuilder.create();
        }
    }

    private void applyHighlighting(Try<StyleSpans<Collection<String>>> highlighting) {
        if(highlighting.get().getSpanCount() > 0) {
            codeArea.setStyleSpans(0, highlighting.get());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
