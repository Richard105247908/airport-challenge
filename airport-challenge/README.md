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

## Usage

An airport needs a weather source. Use `RandomWeather` for the real thing
(about a 1 in 4 chance of a storm), or any other `Weather`.

```java
Weather weather = new RandomWeather();
Airport airport = new Airport(weather);          // default capacity of 5
Airport bigAirport = new Airport(weather, 10);   // custom capacity
```

**Land a plane** with `airport.land(plane);`. It checks, in this order, and throws
`IllegalStateException` if one fails:
1. The airport is full: "Cannot land: airport is full"
2. The weather is stormy: "Cannot land: stormy weather"
3. The plane is already here: "Cannot land: plane is already at the airport"

If all three checks pass, the plane is added to the airport.

**Take off** with `airport.takeOff(plane);`. It checks, in this order:
1. The weather is stormy: "Cannot take off: stormy weather"
2. The plane isn't at this airport: "Cannot take off: plane is not at this airport"

If both pass, the plane is removed from the airport. In a storm, try again once the weather clears.

**Check a plane** with `airport.hasPlane(plane);`, which returns `true` if it is at this airport.

Because `RandomWeather` is random, landing or taking off can throw a stormy-weather
exception even when everything else is fine.

## Architecture notes

**Classes**
- `Airport` holds the planes that have landed, and decides whether a landing or take-off is allowed. It does not decide the weather.
- `Weather` is an interface injected through the `Airport` constructor, so tests can control it. `RandomWeather` is the real one and has a `Random` injected for the same reason.
- `Plane` has no state of its own. Whether a plane is at an airport is answered by `airport.hasPlane(plane)`.

**Rules**
- Capacity has a default of 5 (a constant) and can be overridden through an overload.
- A landing is refused if the airport is at capacity, the weather is stormy, or the plane is already there.
- A take-off is refused if the weather is stormy or the plane is not at this airport.
- Each violation throws `IllegalStateException` with a clear message, and the tests check the message as well as the type.

**Testing**
- JUnit 5 for the tests, Mockito to mock `Weather` and `Random`.
- Mockito could not mock `Random` on Java 25, so the project uses Java 21, matching `pom.xml` and CI.

**What I would do next**
- Give `Plane` its own state (flying or landed), so the plane also confirms what has happened to it.
- Replace `IllegalStateException` with specific exception types.