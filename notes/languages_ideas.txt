Ideas for a shiro interactive console ala irb, or rails c or interactive python

Expressions are parsed and converted into dependency graphs 
The expression ``4 -23'' results in the following declarations

port a Number(4) 
port b Number(23)
port result Subtract(a, b)

result[0] => -19

Basic use of Shiro in dependency graphs 
For example, you can write the
expression

> 3 + 10 
=> 13

Quickly, you'll discover you want to organize you code better ways. You can
organize you code by creating nodes.


node Box begin     
	input length
	input width     
	input height 
end


In this case, box simply holds some values. Now, let's get the box to compute
something


node Box begin
	input length
	input width
	input height
	eval update Multiply(length, width, height) 
end


Now if we define

Box -> b 
b.length(100) b.width(20) b.height(7)	

and then execute 

> b	

The results is: nil

Oops. What happened here? We didn't give box an output.

Let's add a line
node Box begin
	input length
	input width
	input height
	eval update Multiply(length, width, height)
	output volume(update) 
end

Now if we execute


Box -> b
b.length(100)
b.width(20)
b.height(7)	
> b

The result is: 14000

Why does it work this time? It works because we have define an output for Box.

To provide a result, the runtime returns the value of the first output. In this
case, Box only has one output, so it's result is shown.

Let's make one more change to Box to make this idea clear. Add an output port
that prints the of the node.

node Box begin
     input length     
     input width     
     input height     
     eval update Multiply(length, width, height)
     output volume(update)     
     output name("Box")
end

Box -> b

Now this time when we can execute 

> b.name 
=> "Box"
> b 
=> "Box"	

There result is box. We could also type b[1]. The order the outputs are declared
determines their index.

To review, we can create instances of nodes with production operator "->" We 
can also instantiate and initialize a node in one line.


Box -> b(length: 100, width: 20, height: 7)

If for example, we instead wanted to change the value of box to compute only the
area of the base, we could define:

node Box[vol] begin
	input length
	input width
	input height
	option eval base Multiply(length, width)
	option eval vol Multiply(length, width, height)
	output volume(vol)     
	output area(base)
	output name("Box")
end

Box -> b(length: 100, width: 20, height: 7) 
Box -> b(100, 20, 7)
> b.area 
=> nil

The result is nil because we have tried to access a value that is unset. The
default update method for Box is vol. area is only evaluated when base is the
active update method. For our code to work, we need to tell the box which update
method to use. Change the line to:


Box -> b[base](length: 100, width: 20, height: 7)
> b.area => 2000


Let's look at how we can transform a list of values

node 10x begin
	input value Number
	eval update Number(value * 10)
	output newValue Number(update)
end

port aList Array(1, 2, 3, 4)
port result Map(aList, 10x) // use of a prototype

>result
=>[10, 20, 30, 40]

port aList Array(1, 2, 3, 4)

node DayOfWeek begin
	input day
	eval update NumToDay(day)
	output english String(update[0])
	output german String(update[1])
end

port result Map(aList DayOfWeek, @german)
// by passing in the path, the map function knows which output value to use

> result
=> ["Montag", "Dienstag", "Mittwoch", "Donnerstag"]	

If it was omitted:

port result Map(aList DayOfWeek)	
> result
=> [["Monday", "Montag"], ["Tuesday", "Dienstag"], 
["Wednesday", "Mittwoch"], ["Thursday", "Donnerstag"]]

Also, as long as the ports of a node have default values, you can use nodes with multiple inputs

node Invest begin
	input rate Number(0.10)
	input principle Number(5000)
	input duraction Number
	eval update SimpleInterest(rate, principle, duration)
	output interest(update[0])
	output value(udpate[1])
end

port aList Array(1, 2, 3, 4)
port result Map(aList, Invest, @interest)

> result
=> [500, 500, 500, 500]

Selecting the result of the value port instead, we get

port result Map(aList, Invest, @value)

> result
=> [5500, 6000, 6500, 7000]

We can also instantiate and modify a node and use it.

Invest -> i(rate:0.05)

port result Map(aList, i, @value)

> result
=> [5250, 5500, 5750, 6000]

I have shown how to describe simple parametric systems with ports
and nodes. One of the strengths of parametric systems is the ability to
change a port's value and have the whole system update. How easy it is to change
a parametric system makes it a powerful tool for exploring alternatives.
The programmer can easily change the value of a port and see how it affects
the design.

Shiro has language level features to express and manage alternatives.
Let's reuse our Invest node and create a shiro program that uses alternatives

node Invest begin
	input rate Number(0.10)
	input principle Number(5000)
	input duraction Number(1)
	eval update SimpleInterest(rate, principle, duration)
	output interest(update[0])
	output value(udpate[1])
end

node InterestRates[low] begin
	option port low Number(0.05)
	option port high Number(0.1)
end

Because we have defined more than one node and want to connect them together, we use a graph

graph Money begin
	InterestRate -> i

	Invest -> investment(rate: i.active)
end

state lowRate begin
	Graph Money
	i[low]
end

> lowRate
=> i: {low: 0.05}
investment: {rate: 0.05, principle: 5000, duration: 1, interest: 250, value: 5250}	

Literal
------------------
nil - a multi-function with no inputs or outputs.
String - "Hello world" "Hellow'ggood' day."
Number - 1, 2, 10, 1000000 1.233423423 -11.212
List/Array - [0, "green", 5, false]
Boolean - true, false
Replicants - <"Jeff", "Joe">
Path - @node.port.value
Range [start..end] by increment
Range (start..end) by increment
Range [start..end) by increment

Objects
User defined nodes
Anonymous node - {x: "Hello", w: "world", z: [100, 100, 100]}
	Translates to
	node anon begin
		input x String("Hello")
		input w String("world")
		input z Array(100, 100, 100)
	end

----
We can definte solution sets, or sets of states with:

states <name> begin
	graph myGraph
	<path> <list of values to be assigned to the path>
end

By using a list of values, a common syntax can be used for setting subjunctive nodes
and ports.

In the past we have used the following syntax to define individual states

state myState begin
	Graph myGraph
	subjunctPath[activeOption]
end

To define solution sets, I'm building from this "familar" syntax by defining the plural states.

states <name> begin
	graph myGraph
	<path to subjunct> [<active option>] // Notice this is a one element literal list. Before it was akin to a map/array accessor, but if we switch it to a list. It's just a list with one element.
end

I think it makes sense to include a short hand syntax for saying give me all the states with all
the values of subjuncts. * means all of the options of a subjunctive node should be enumerated

states <name> begin
	graph myGraph
	<path to subjunct> [*]
end

We can use ranges and set the values of nested nodes.

states myawesomesolutions begin
	graph myprogram
	investment.interest [0.1..0.5] by 0.1 begin
		pathIninvestment [green]
	end
end

We can also use other nodes to calculate the lists of values to use. We can use
Shiro to configure Shiro.

states <name> begin
	graph myGraph
	portTobeVaried options( pathtoPass(x: 1, y: 2), @areas)
end

The options multifunction proves a way for an arbritrary function to be used
to generate the list of values to be used as 

@areas specifies the port to use as the set of values
When the runtime evaluates the states declaration. It enumerates
all of the possible configurations of the graph and evaluates each of them in turn.

A couple comments about opportunities for optimization, with the need to evaluate many similar
graphs some ports will be recomputed unnecessarily. To prevent that from happening, 
I think I will add a cache, so that values ports that have the exact same input
values can use a cached value. Also, it is possible to use
multiple threads to compute each of the graphs concurrently. I need to work out this 
will work exactly, but it should be possible.

There is also a pre-computation step where the graph represented by
the passed path needs to be evaluated

----
node Point begin
	input x value(10)
	eval update(x[0])
end


node Rect begin
	input origin Point
	input length Number
	input width Number
	eval update drawRect(origin, length, width)
	output area
	// need to output the object
end

node Box begin
	input length
	input width
	input height
	Rect -> base(origin: {x:1, y:2}, length: length, width: width)
	
	mult Multiply(base.area, height)

	output volume Number(mult) // mult is the syntactical sugar for mult[0]
	/**
		What's happening behind the scenes?

		An output port named volume is declared as type Multiply
	**/
	// or
	// output volume = base.area * height
end

Range -> r(start: 0, end: 2, step:0.2)


graph test begin
	Point -> p(x: 100, y:130)
	Rect -> r

	r.origin(p)
end

states begin
	p.x(0..10 by 1) // 1, 2, 3, 4, 5, 6, ... 10
	generate p.y in r
	show p.x > 1 AND p.y < 3
end

