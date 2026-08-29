[![api](https://maven-badges.sml.io/sonatype-central/org.lidiuma.math/math-api/badge.svg?style=flat&subject=api&color=blue)]([https://maven-badges.sml.io/sonatype-central/org.lidiuma.math/math-api](https://maven-badges.sml.io/sonatype-central/org.lidiuma.math/math-api))
[![traits](https://maven-badges.sml.io/sonatype-central/org.lidiuma.math/math-traits/badge.svg?style=flat&subject=traits&color=blue)]([https://maven-badges.sml.io/sonatype-central/org.lidiuma.math/math-traits](https://maven-badges.sml.io/sonatype-central/org.lidiuma.math/math-traits))
# MathAPI
This project provides set of interfaces that represent mathematical classes like `Vector`, `Matrix`, `Quaternion`, `VectorOps`, etc.
Allowing integration between different libraries and frameworks (mainly focused on game development).

The project is split in two modules; `api` and `traits`.
- The `api` module provides a Standard read-only set of interfaces for values. For example `Vector3` having only `x()`, `y()`, `z()`.
- While the `traits` module provided operations for the values present in `api`. For example `Vector3Ops` has methods like `dot()`, `cross()`, etc.

This split allows the `api` to have different implementations, allowing mutable, immutable, OOP, or FP, not forcing any specific standard,
while still retaining compatibility with different libraries.\
While `traits` follows the Java language evolution towards Functional Programming/Data Oriented Programming and immutability,
and hopefully in the future getting new language features like operator overloading.

## Java Version
**Java 17**\
I could not go lower since I wanted to offer modularity support and I wanted to have the nullability guarantees provided by `JSpecify` (this being the only dependency).

## Current Work
The current objective is to gather as much feedback as possible and have the `1.0` release for `api`. Early access is already available on maven to experiment with.\
Mainly I'd like to get feedback about the `shapes` package, I'm unsure if I should split it into its own thing, but the interfaces inside are too useful to keep separated.

For the `traits` module, the idea is to release `0.1`, and wait for Project Valhalla to evolve before commiting to a `1.0` release,
since the Java implementation of type-classes could vary a lot impacting the code greatly.\
Also this is the module that likely requires the most feedback, there might be useful methods I'm not aware of,
or people might not agree with the currently implemented ones, so please, you are welcomed to give feedback!

## Feedback & Contact
Since no standard can be made alone, please provide as much feedback as possible, don't like something? Let me know!\
And if you want to contact me privately for any feedback or questions, you can write me at [contact@lidiuma.org]().