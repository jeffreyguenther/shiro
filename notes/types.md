What does a type in Shiro mean?
How do we handle situations like:

```
node Rectangle begin
	...
	eval update Rectangle(...)
end
```

Are nodes and ports the same thing?
If they are, we can't use the same name for a node definition and a port definition.
Internally, we figure out which is which. 

Where the evaluate method is the same as the Rectangle:

```
node Rectangle begin
   ...
   eval(...)
end
```

Where you want to name the evaluate method for some reason

```
node Rectangle begin
   ...
   update1 eval(x, y, width, height) 
end
```

When you want to be able to choose between two update methods.

```
node Rectangle[update1] begin
   ...
   option update1 eval(x, y, width, height) 
   option update2 eval(x, y, width * 2, height) 
end
```

Specifying the eval's access modifier is also legal, though it doesn't make sense
to make it an input.

```
node Rectangle[update1] begin
   ...
   option output update1 eval(x, y, width, height) 
   option output update2 eval(x, y, width * 2, height) 
end
```
