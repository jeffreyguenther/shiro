# Types

Given the following definitions:

```
node A begin
  output x Double(1.0)
end

node B begin
  output x Double(1.0)
end

node C begin
  output x String("1.0")
end
```

Functions `A` and `B` are _output compatible_. Two functions are _output
compatible_ if and only if the names, positions, and types of their outputs
are the same. That is they share the same output interface.

Functions `A` and `B` are _input compatible_. Two functions are _input
compatible_ if and only if the names, positions, and types of their inputs
are the same. That is they share the same input interface.

Functions `A` and `B` are _compatible_. Two functions are _compatible_ if and
only if they are _output compatible_ and _input compatible_. Functions that are
compatible can be used in place of each other.

Primitive functions like `Double` and `String` that have an input defined pass
the value through when executed. Literal `Double`s and `String`s directly assign
the value to the output set. The type definition for `Double` is
`<[Double]:[Double]>`

Let's examine what this means in practice:

```
node A begin
  output x Double(1.0)
end

node B begin
  output x Double(4.0)
end

node X begin
  input a A
  output o Double(a.x)
end

x X
x.o // outputs 1.0
```

Now, what if we want to define `X` so that an instance of `A` or `B` can be
passed in?

```
node A begin
  output x Double(1.0)
end

node B begin
  output x Double(4.0)
end

node X begin
  input a <[]:[x: Double]> // abstract type definition
  output o Double(a.x)
end

x X(a: ~B()) //sets a to an instance of B
x.o // outputs 4.0
```

`input a <[]:[x: Double]>` says that `a` must be passed a function instance that
has no inputs and whose first output is named `x` and is of type `Double`.

```
node Box begin
  input length Double
  input width Double
end

node F begin
  input box Box
end

a Double(100.0) // instantiate a Double with value 100.0
b Box // instantiate a Box

t F(~b) // set box to the instance b
```

or

```
node Box begin
  input length Double
  input width Double
end

node F begin
  input box <[length: Double, width: Double]:[]>
end

a Double(100.0) // instantiate a Double with value 100.0
b Box // instantiate a Box

t F(~b)
```

or

```
node Box begin
  input length Double
  input width Double
end

node Square begin
  input length Double
  input width Double
end

node F begin
  input box Box
end

a Double(100.0) // instantiate a Double with value 100.0
b Square(100.0, 100.0) // instantiate a Square instead of a Box

t F(~b) // this is legal b/c Square and Box are compatible types.
```
