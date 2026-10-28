# Airport Challenge (Java)

A Java 21 / JUnit 5 / Mockito port of the Makers [airport_challenge](https://github.com/makersacademy/airport_challenge), built test-first.

## Stack
- Java 21, Maven
- JUnit 5, Mockito, AssertJ
- JaCoCo (coverage), GitHub Actions CI

## What it does
Models an air traffic controller instructing planes to land at and take off from an airport. Landing and take-off are blocked in stormy weather, and landing is blocked when the airport is full.

## User stories
```
As an air traffic controller
So I can get passengers to a destination
I want to instruct a plane to land at an airport

As an air traffic controller
So I can get passengers on the way to their destination
I want to instruct a plane to take off from an airport and confirm that it is no longer in the airport

As an air traffic controller
To ensure safety
I want to prevent landing when the airport is full

As the system designer
So that the software can be used for many different airports
I would like a default airport capacity that can be overridden as appropriate

As an air traffic controller
To ensure safety
I want to prevent takeoff when weather is stormy

As an air traffic controller
To ensure safety
I want to prevent landing when weather is stormy
```

## Java translation notes
| Ruby / RSpec | Java |
|---|---|
| `double` for random weather | Mockito mock (or a hand-written stub) of a `Weather` interface |
| `rand` for weather | `Random` inside a `RandomWeather` implementation |
| Default capacity, overridable | Two constructors, or a constant plus an overload |
| `raise` | Throw `IllegalStateException`; test with `assertThrows` |
| SimpleCov | JaCoCo |

## Edge cases to cover
- A plane that is already flying cannot take off again
- A plane that is already landed cannot land again
- A plane can only take off from the airport it is in
- A landed plane must be in an airport; a flying plane must not be in one

## Running it
```bash
./mvnw test      # run the tests
./mvnw verify    # tests + coverage report at target/site/jacoco/index.html
```

## Progress (update as you go)
- [x] Land a plane
- [x] Take off and confirm it has left
- [x] Full airport blocks landing
- [x] Default capacity, overridable
- [x] Stormy weather blocks take-off
- [x] Stormy weather blocks landing
- [x] Edge cases
- [x] Coverage above 95%
- [x] Bonus: feature test landing and taking off several planes

## Architecture notes
_Fill in as you build: why the classes are split the way they are, and how weather is injected._
