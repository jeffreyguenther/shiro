```
node Point begin
	input x Double
	input y Double
end


Point -> p
p.x
p.y
```

We need to be able to treat nodes the same as ports as both are multi-functions

```
Point // refers to the prototype
p // returns the node instance
p.x[0] // returns the 0th result of port x
p.x["value"] // returns the result named "value"
p.x // returns the 0th result of port x
```
How do I access the args of a port?
Is there a case where I would ever would want to access the args?
Can I make the syntax for access a port's values and a node's values consistent?

```
node A begin
	input in Double
	port internal Double
	output out Double
end

A -> a
a.in // returns the the first result of the port in.
a.internal // returns an error, the port is not accessible
a.out  // returns the first result of the port in

a.in(2.3) // sets the the value of port in
a.out(2.3) // returns an error

a["in"](3.4) // could access the in port
a["out"] // could access the out port
```

OR to access the 

```
x(3.2) sets the first argument of the port
x[0] reads the first result of the port
x["value"] reads the value of the result named "value"

A -> a(in: 3.3) sets the vale of in
```

How do I set the first arg of a port via the statement?

```
A -> a(in: (33.3, 33.3))
```
applying the same idea to a port

```
x(arg1: 3.3, arg0: 4.3)
```

This would allow a person to use positional, or named arguments for both ports and nodes

```
~a.in // returns a reference to the port in
~a["in"] // returns a reference to the port in
```

If we expand the definition of A to:

```
node A begin
	input in Double
	port internal Double
	output out Double

	B -> b
	node B begin
		input i Double
		output out Double
	end
end
```

and write `A -> a`

```
~a.b.i // returns a reference to the port i
a.b.i // returns the 0th results of the port i
a.b["i"] // returns the value of 0th result of port i
a.b["i"]["value"] // returns the result named "value"
a.b["i"](3.3) // sets the value of the port named i
```

If tool more than one argument:

```
a.b["i"](3.3, 3.34, true) // sets the value of the port named i
```

I think this is a little confusing...

Currently, [<value>] is used to read from an output
(<value>) is used to set an input.

How do we read from an input? That's the question. For simple ports it doesn't matter
as much because there's often no reason to read their inputs. If I try to unify
the syntax of nodes and ports, I need a way to read from the inputs of multi-functions
in general.

```
A.in is how to read from the inputs of a node.
A.in.argName could be how read from the inputs of a multi-function in general
```

This works if all the args are named. Some args are not named which means they
won't be accessible. It's up to the multifunction's author to name the args or
else the args can't be read. That said, I'm not sure why a person would want to
read from a port's args when when they can just access the value being passed in
the first place.

Perhaps, it would make sense to introduce another keyword to allow access to the
inputs. 

```
a.inputs["in"] // return the 0th value of port in
```

```
node A begin
	input in Double
	port internal Double
	output out Double

	B -> b
	b.inputs["i"](3.3333) these statements are equivalent
	b.i(3.3333)
	
	b.outputs["out"] these statements are equivalent too
	b.out

	node B begin
		input i Double
		output out Double(i.inputs[0]) // takes whatever value is passed to i in the first position and passes it to out
	end
end
```

To summarize, given the definition:

```
node Point begin
	input x Double
	input y Double
	output sum Double(x + y)
end
Point -> p

~p returns a reference to the node
p returns the value at result 0 for the node
p.outputs[0] are equivalent b/c the path terminates on a port, the result 0 is returned.
p.outputs["sum"] also gives the same result
p[0] is an invalid path
p["sum"] is also invalid
p.outputs["sum"].outputs[2] returns the result at position 2 of the port called sum
p.outputs["sum"][2] is invalid

Point -> p(x: 3.32) creates an instance of p and sets the value of the input port named x
p.inputs["x"](3.32) sets the value of the input port named x
p.x(3.32) sets the value of x at arg 0
p.x(argName: 3.33) sets the value of x at argName

p.outputs[0] returns the value of result 0 of sum `p.outputs[0].outputs[0]` is the result
p.outputs["sum"] returns the value of result 0 of sum

p.x.inputs[0] returns the value at arg 0
p.x.inputs["argName"] returns the value at "argName"
p.inputs[0](3.32)
p.inputs[0](argName: 3.33) sets argName
p.inputs["x"](3.32) sets arg 0
p.inputs[0](argName: 3.33) sets argName

p.x returns the value at result 0 of x
p.x["name"] returns that value at result named "name" of x
p.x.outputs["name"] returns the value at result named "name" of x
p.x.outputs[0] returns the value at result 0 of x
```

In side the node:

```
node Point begin
	input x Double
	input y Double
	output sum Double
	sum(x + y)
	sum(value: x + y )
end

x returns the value of result 0
x[0] returns the value of result 0
x["value"] returns the value of result named 'value'
```

I need to add support for subjunctive nodes and options to this definition of paths.

Given the definition:

```
node Options begin
	option a Double(10.0)
	option b Double(11.0)
	output result (active)
end	

Options -> o

port sum Add(o.result, 3.0)
```

`active` can be used inside `Options` to refer to the active `option`. All the
previous rules apply.

```
node Options begin
	option a Double(10.0)
	option b Double(11.0)
	output result (active)
end	

Options -> o
port sum Add(o.result, 3.0)
```
As far as I can tell, there isn't a need for `this`. I'm not aware of a situation
where a port would need to be disambiguated this way.

Do we need the input and output keywords? Can a path simply be a path and leave
determining whether assignments are legal to logic in the assignment?

Another idea, given the definition:

```
// Multiply (a, b) = <value>

node Point begin
	input x Double
	input y Double
	output sum Double(x + y)
end
Point -> p
~p returns a reference to the node
p returns the value at result 0 for the node, which in this case does not exist

Point -> p(x: 3.32) creates an instance of p and sets the value of the input port named x
p.["x"](3.32) sets the value of the input port named x
p.x(3.32) sets the value of x at arg 0
p.x(argName: 3.33) sets the value of x at argName

p[0] returns the value of result 0 of sum
p["sum"] returns the value of result 0 of sum

p.x[0] returns the value at results 0
p.x["argName"] returns the value at "argName"

p.x returns the value at result 0 of x
p.x["name"] returns that value at result named "name" of x
p.x["name"] returns the value at result named "name" of x
p.x[0] returns the value at result 0 of x
p.x.outputs[0] returns the value at result of x
```

In order to access the inputs of a multi-function, you need to use the name or
use the index. The downside of this approach is inputs and outputs would need to
use a common set of indices. I think this is confusing. I'm not sure this is a good idea.
I like the cleanliness of being able to distinguish an input from an output or
an argument from a result.
