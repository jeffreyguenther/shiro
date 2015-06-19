How do I evaluate a node passed as an argument to a multi-function?

Given the definition:

```
node Interest begin
	input principal Double
	input rate Double
	input time Double
	output value Double(p * r * t)
end

Interest -> i
i.principal(100.0)
i.rate(0.10)

port times List(1.0, 2.0, 3.0)
port returns Map(times, ~i, @time, @value) // [10.0, 20.0, 30.0]
```

I define a node `Interest` with inputs `principal`, `rate`, and `value`. Then I
make an instance of the node called `i`, which I initialize with values for
`principal` and `rate`. I create a list of times and then map over the list to
apply `i` to each element in the list with `port returns Map(list, i, @time,
@value)`. `@time` selects the input of the passed node to which the list element
is assigned. `@value` selects the output to use in the list. For each element in
the list, `i` is applied giving the result of `[10.0, 20.0, 30.0]`.

Is it acceptable for a node reference to refer to a port in the graph?
If yes, we must guarantee the referenced port and it's upstream dependencies are
all evaluated before the node is evaluated.

For example:

```
node A begin
	output x Double(0.03)
end

node B begin
	input x Double
	output y Double(x * 2)
end

node Interest begin
	input principal Double(100.0)
	input rate Double
	input time Double

	output value Double(b.y * r * t)
end

A -> a
B -> b
b.x(a.x)

Interest -> i
i.principal(100.0)
i.rate(b.y)

port times List(1.0, 2.0, 3.0)
port roi Map(times, ~i, @time, @value)
```
This program requires that `a` and `b` are evaluated before being used in `Map`.

For simplicity of implementation at the moment, I think it makes sense not to
allow a lambda to reference other parts of the graph. If I do want to support
this type of interaction, I could create a temporary graph for each element. If I
cached the results of each function, I could prevent re-execution of ports on each
graph evaluation.

If I keep the execution of `i` isolated from the rest of the graph, things are
cleaner. It is also possible to keep dependencies in the graph only between
ports which is what we want. We don't want dependencies between nodes. 

If want to implement a type that takes a multi-function reference as an argument,
what type do I put in the arg?

First, the type of the multi-function only matters if the runtime enforces
the type of the arguments, which is a good idea because that's why we have types
in the first place.

Second, the only place type declarations really matter is in multi-functions.

Type checks can be done on a per-argment basis inside the multi-function. Errors can
be handled assignment time to allow the runtime to give nice errors.
