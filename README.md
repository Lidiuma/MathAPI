# MathAPI
Standard set of interfaces that represent mathematical classes like `Vector`, `Matrix`, `Quaternion`, etc.\
With a main focus on game development and immutability.

## Objective
The objective for this project is to provide a lingua franca for different libraries and frameworks,
allowing any implementations that respects the API to be used anywhere, facilitating development.

## Java Version
**Java 25**\
Such high version because I wanted to include FFM, since converting the objects to native memory is really useful for C bindings and frameworks.\
There might be a chance I'm able to backport this to 17, but take this as a far-stretched possibility.

## Current Work
The current objective is to gather as much feedback as possible and have the first early access release.\
I think the current set of interfaces is good enough, only slight cleaning should be done and add missing documentation.
Once all of this is done, I can start working on a first implementation that can be used as a reference.

## Future Work
There are types, like `Vector` and `FloatingVector` that I cannot represent nicely with the current Java features,
so I'm considering a future version (v2 or something along the lines) that can take advantage of future Type-Classes.\
The only issue is these are still a few years away.

## Feedback
Since no standard can be made alone, please provide as much feedback as possible, don't like something? Let me know!

## Contact
For any feedback or questions, you can write to [contact@lidiuma.org]().