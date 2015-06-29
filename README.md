Shiro
=====

Shiro is a data flow programming language designed to be embedded into
applications to help to support reuse and the exploration of alternatives. The
language is part of Jeff's PhD thesis. This is research code. Use with a sense
of humour and lots of patience.

Usage
=====

The Shiro runtime is designed to be used as the computational engine for
applications like parametric CAD tools, vector editors, and spreadsheets. The
runtime provides an API for manipulating the runtime.

#### Examples
In `examples`, you'll find folders with example Shiro programs. Checked examples
run in **Shiro Playground**.

* Color_Schemes - shows how to create a color scheme
	* [ ] `ColorScheme_OnColor.sro` creates alternatives on individual colors
	* [ ] `ColorScheme_OnScheme.sro` creates alternatives on the entire scheme

* Data_Analysis - example of processing a CSV file and viewing the result in a table
	* [ ] `poverty.sro` analysis of the world's population in poverty

* Drawing - shows how to draw in Shiro
	* [ ] `Costume_Selection.sro` shows how to render differty costumes for a character
	* [x] `FlowerOfLife.sro` uses traces of the Flower of Life, a patterns of circles to create images
	* [x] `shapes.sro` is collage of the shapes you can draw in Shiro

* Finance - financial calculations
	* [x] `compund_interest.sro` demonstrates `CompoundInterest` node
	* [x] `Investment.sro` shows how to compare two investment products

* Logo - create alternative versions of a logo
	* [ ] 'EverClean_logo.sro' defines a design for a logo

* Old_Examples - examples written in the old syntax to be ported

* Syntax_Examples - short snippets demonstrating bits of the syntax
	* [x] Includes - demonstrates the use of `include`
	* [x] Options - desmonstrates the use of `option`
	* [ ] Recursion - demonstrates how to do recursion
	* [x] `arthimetic.sro` demonstrates basic arithmetic
	* [x] `list.sro` syntax for a literal list
	* [x] `map.sro` demonstrates how to use the map function to iterate over a list.


Integrating Shiro
=================

Shiro is designed to be integrated into applications as a scripting language. See the [Playground](https://github.com/jrguenther/shiro/tree/master/src/main/java/org/shirolang/playground)
for an example of what it takes.

The latest version of Shiro is 0.5.0

### Add via maven

|Group ID | Artifact ID | Version |
|---------|-------------|---------|
|org.shirolang|shiro    |0.5.0    |

### Add manually

To add Shiro to your project manually, add the [fat jar](https://github.com/jrguenther/shiro/releases/download/v0.5.0/shiro-fat-0.5.0.jar)
to the classpath of your application.

Development
===========

Shiro is implemented in Java. [Gradle](http://www.gradle.org/) is used as the
build system and dependency manager. The interpreter depends on [ANTLR
4.5](http://www.antlr.org/) to generate the parser, lexer and parse tree event
listeners. `build.gradle` is setup to automatically generate the Java code at
compile time. You can use gradle from the commandline or use Netbeans or
Eclipse, as both IDE's support gradle projects.

You can start the **Shiro Playground** application that allows you to load and
run Shiro code using this command:

    $ gradle run

Currently, you cannot run execute Shiro code from the commandline. If a REPL is
a priority for you, add an issue and we can explore what it will take to make
one.


To create an executable for your OS, use the javafx-gradle plugin:

    $ gradle jfxDeploy

The executable bundle will be found in `build/distributions/bundles`

A helpful command to remember if you just want to regenerate the parser, lexer,
and base listener is:

    $ gradle generateGrammarSource

Questions can be sent to shiro-lang@googlegroups.com or
[Jeff](mailto:jguenthe@sfu.ca). If you have taken the time to play with the
language, please drop me a line and let me know what you think.

Ways You Can Help
=================

Because this code is being developed for my thesis, I'm responsible for its
design and implementation. That said, there are ways you can help give the
language the polish it is needed.

* Give feedback on both the implementation and the language syntax
* Help write functions for the standard library

  This is one of the biggest and most important parts of the project after the 
  basic language design is complete. We need a good library of multi-functions 
  to make the language helpful to people in a variety of domains.

Acknowledgements
-----------------

This project is the result of research being conducted at 
[Simon Fraser University](http://www.sfu.ca/)'s 
[School of Interactive Arts and Technology](http://www.siat.sfu.ca/) in the 
[CZSaw Research Group](http://czsaw.iat.sfu.ca/) and 
the [Computational Design Group](http://www.computationaldesign.ca/). 

#### People
* [Jeffrey Guenther](http://jeffreyguenther.com) - Designer and Implementer
* [Robert Woodbury](http://www.siat.sfu.ca/faculty/profile/rob-woodbury) - PhD Committee member
* [Chris Shaw](http://www.sfu.ca/~shaw/) - PhD Supervisor
* [Siniša Kolarić](http://www.sfu.ca/~skolaric/) - Research Group Colleague
* [Ankit Gupta](http://www.linkedin.com/pub/ankit-gupta/37/b7b/851) - Research Group Colleague

Mailing list: shiro-lang@googlegroups.com

License
-------
Shiro is licensed under the [MIT license](http://opensource.org/licenses/MIT)
