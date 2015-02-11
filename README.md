Shiro
=====

Shiro is a data flow programming language designed to be embedded into applications to help to support reuse and the exploration of alternatives. The language is part of Jeff's PhD thesis. This is research code. Use with a sense of humour and lots of patience.

Getting Started
===============

Shiro is implemented in Java. [Gradle](http://www.gradle.org/) is used as the build system and dependency manager. The interpreter depends on [ANTLR 4.5](http://www.antlr.org/) to generate the parser, lexer and parse tree event listeners. `build.gradle` is setup to automatically generate the Java code at compile time. You can use gradle from the commandline or use Netbeans or Eclipse, as both IDE's support gradle projects. With Netbeans, change the Build Script Evaluation Strategy to "IDEA plugin based."

You can run the Shiro interpreter using this command within the shiro subdirectory

    $ gradle run

This will start the **Shiro Playground** application that allows you to load and run Shiro code. Currently, you cannot run execute Shiro code from the commandline.

To create an executable for your OS, use the javafx-gradle plugin:

    $ gradle jfxDeploy
The executable bundle will be found in `build/distributions/bundles`

A helpful command to remember if you just want to regenerate the parser, lexer, and listeners is:

    $ gradle generateGrammarSource

Questions can be sent to shiro-lang@googlegroups.com or [Jeff](mailto:jguenthe@sfu.ca). If you have taken the time to play with the language, please drop me a line and let me know what you think.

Ways You Can Help
=================

Because this code is being developed for my thesis, I'm responsible for its design and implementation. That said, there are ways you can help give the language the polish it is needed. The scope of my thesis is quite narrow, but this language should be helpful to people outside of visual analytics.

* Give feedback on both the implementation and the language syntax
* Help create a REPL for a better commandline experience
* Help write functions for the standard library
  This is one of the biggest and most important parts of the project after the basic language design is complete. We need a good library of multi-functions to make the language helpful to people in a variety of domains.

Acknowledgements
-----------------
This project is the result of research being conducted at [Simon Fraser University](http://www.sfu.ca/)'s [School of Interactive Arts and Technology](http://www.siat.sfu.ca/) in the [CZSaw Research Group](http://czsaw.iat.sfu.ca/). 

#### People
* [Jeffrey Guenther](http://jeffreyguenther.com) - Design and Implementer
* [Chris Shaw](http://www.sfu.ca/~shaw/) - PhD Supervisor
* [Robert Woodbury](http://www.siat.sfu.ca/faculty/profile/rob-woodbury) - PhD Committee member
* [Siniša Kolarić](http://www.sfu.ca/~skolaric/) - Research Group Colleague
* [Ankit Gupta](http://www.linkedin.com/pub/ankit-gupta/37/b7b/851) - Research Group Colleague

Mailing list: shiro-lang@googlegroups.com

License
-------
Shiro is licensed under the [MIT license](http://opensource.org/licenses/MIT)
