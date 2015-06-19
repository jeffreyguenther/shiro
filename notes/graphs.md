// default graph
x Double(0.0)
y Double(12.3)
sum Multiply(x, y)
5 + x

node A begin
	input a Double(100.0)
	factor Integer(2)
	output b Multiply(a, factor)
end

graph g begin
	a Double(3.459)
	b Double(12.34)

	A -> a1(a: a)
	A -> a2(b)
end
