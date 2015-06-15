How do we handle nested nodes with options?

When the nodes are defined separately:

```
node B begin
    option b1 Double
    option b2 Double
end

node A begin
    option B -> o1
    option B -> o2
end

graph g begin
    A -> node
end

state stateName begin
    Graph g
    node[o1] begin
        o1[b2]
    end
end
```

or when the nodes are defined as nested nodes:

```
node A begin
    node B begin
        option b1 Double
        option b2 Double
    end

    option B -> o1
    option B -> o2
end

graph g begin
    A -> node
    A.B -> b
end

state stateName begin
    Graph g
    node[o2] begin
        o2[b2]
    end
    b[b1]
end
```
