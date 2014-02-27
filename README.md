Shiro
=====

Shiro is a data flow programming language designed to be embedded into applications to help to support reuse and the exploration of alternatives. The language is being designed as a part of Jeff's PhD thesis. This is research code. Use with a sense of humour and lots of patience.

Getting Started
===============

Shiro is implemented in Java. [Gradle](http://www.gradle.org/) is used as the build system and dependency manager. The interpreter depends on [ANTLR 4.1](http://www.antlr.org/) to generate the parser, lexer and parse tree event listeners. `build.gradle` is setup to automatically generate the Java code at compile time. You can use gradle from the commandline or use Netbeans or Eclipse, as both IDE's support gradle projects.

You can build the Shiro interpreter using this command within the shiro subdirectory

    $ gradle builds

A helpful command is to remember if you just want to regenerate the parser, lexer, and listeners is:

    $ gradle generateGrammarSource

In the `example_code` folder, you'll find `example.sro`. It is a simple example of the language syntax. Currently, when the code is interpretted a large amount of debugging output is shown. It'll help you get a sense of what is actually going on inside the system. More examples are coming shortly. Pass example.sro as the first argument on the commandline to see the output.

    $ gradle run -Pargs="example_code/example.sro"

Questions can be sent to shiro-lang@googlegroups.com or [Jeff](mailto:jguenthe@sfu.ca). If you have taken the time to play with the language, please drop me a line and let me know what you think.

Ways You Can Help
=================

Because this code is being developed for my thesis, I'm responsible for its design and implementation. That said, there are ways you can help give the language the polish it is needed. The scope of my thesis is quite narrow, but this language should be helpful to people outside of visual analytics.

* Give feedback on both the implementation and the language syntax
* Help create a REPL for a better commandline experience
* Help write multi-functions for the standard library
  This is one of the biggest and most important parts of the project after the basic language design is complete. We need a good library of multi-functions to make the language helpful to people in a variety of domains.

Acknowledgements
-----------------
This project is the result of research being conducted at [Simon Fraser University](http://www.sfu.ca/)'s [School of Interactive Arts and Technology](http://www.siat.sfu.ca/) in the [CZSaw Research Group](http://czsaw.iat.sfu.ca/). 

#### People
* [Jeffrey Guenther](http://jeffreyguenther.com) - Design and Implementer
* [Robert Woodbury](http://www.siat.sfu.ca/faculty/profile/rob-woodbury) - PhD Commitee member
* [Siniša Kolarić](http://www.sfu.ca/~skolaric/) - Research Group Colleague
* [Ankit Gupta](http://www.linkedin.com/pub/ankit-gupta/37/b7b/851) - Research Group Colleague

Mailing list: shiro-lang@googlegroups.com

License
-------
Shiro is licensed under the [MIT license](http://opensource.org/licenses/MIT)