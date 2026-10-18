# Airport Challenge: Code Review Checklist (Java version)

The Makers review rubric translated for Java 21, JUnit 5 and Mockito. Put this in `docs/review.md` of your project and tick things off as you go.

## Step 0: Checkout and run the tests
- [ ] `./mvnw test` passes from a clean checkout
- [ ] `./mvnw verify` produces a coverage report (`target/site/jacoco/index.html`)
- [ ] Try it by hand in `jshell --class-path target/classes`. Is the structure easy to follow?

## Step 1: How far did you get?
- [ ] Plane status
- [ ] Plane landing
- [ ] Plane take-off
- [ ] Storms prevent landing
- [ ] Storms prevent take-off
- [ ] Full airport cannot land planes
- [ ] Variable and default capacity
- [ ] Exceptions for inconsistent actions
- [ ] Bonus: a feature (integration) test that lands and takes off several planes

## Step 2: README
- [ ] Explains your approach, and where you got to if unfinished
- [ ] Shows a usage example from the user's side, for example a `jshell` session:

```
jshell> var airport = new Airport()
jshell> var plane = new Plane()
jshell> airport.land(plane)
jshell> airport.hasPlane(plane)
$4 ==> true
```

## Step 3: Tests (JUnit 5 and Mockito)

| Ruby / RSpec rubric point | Java equivalent |
|---|---|
| Named `subject` with `described_class` | A named field (`private Airport airport;`) created in `@BeforeEach`, in a class called `AirportTest` |
| `describe` / `context` blocks | `@Nested` classes with `@DisplayName`: use one for a behaviour ("landing"), one for a state ("when it is stormy") |
| `before` blocks | `@BeforeEach`. One inside a `@Nested` class runs after the outer one |
| Avoid vacuous tests | Don't assert on a mock's own stubbed behaviour. Put the test in the class whose state you are checking |
| Ensure sufficient unit tests | Test that the airport tells the plane to land with `verify(plane).land()`, and with several planes, that the *correct* one is removed on take-off |
| One expect per `it` | One behaviour per `@Test`. Don't `verify` something you only stubbed |
| Prefer `allow` over `expect` when stubbing | `when(weather.isStormy()).thenReturn(true)` with no `verify` unless the call is the point of the test |
| Stub randomness | Mock the `Weather` interface. A test must never depend on a real random result |
| Redundant `respond_to` tests | Not needed. The compiler already checks that a method exists |
| The multi-line `raise_error` trap | Check the message, not just the type (see below) |

Check the exception message, not only that one was thrown:

```java
var ex = assertThrows(IllegalStateException.class, () -> airport.land(plane));
assertEquals("Cannot land: airport is full", ex.getMessage());
```

Mockito note: with `MockitoExtension`, an unused stub makes the test fail (strict stubs). That is useful, because it flags stubs you don't need.

## Step 4: Application code

| Rubric point | Java equivalent |
|---|---|
| Names match the domain | `PascalCase` classes, `camelCase` methods, `UPPER_SNAKE_CASE` constants. Use `land` and `takeOff`, not clever names |
| Remove commented-out code | Delete it. Git remembers |
| Guard clauses | Throw early: `if (weather.isStormy()) throw new IllegalStateException(...);` then carry on. No nested `if/else` |
| Implicit boolean return | `return planes.size() >= capacity;`, not `if (...) return true; else return false;` |
| Don't expose internals | `private final List<Plane> planes`. Don't return the live list. Offer `hasPlane(plane)` or return `List.copyOf(planes)` |
| Single responsibility | `Weather` is its own interface and is injected through the constructor. `Airport` doesn't decide the weather |
| Methods do one thing | Pull small private helpers out, for example `isFull()` and `contains(plane)` |
| No magic numbers | `private static final int DEFAULT_CAPACITY = 6;` |
| Symbols over strings | Use an `enum` (for example `Outlook.STORMY`) or a boolean, never compare strings |
| Command / query separation | Commands are verbs (`land`, `takeOff`) and change state. Queries return a value and never change state. Java convention allows `isFull()` and `hasPlane()`; the rule that queries must not alter state still applies |
| `attr_reader` / `attr_accessor` | `private final` fields, no public setters. Use `land()` rather than `setLanded(true)` |
| Redundant code | Delete unused fields, imports, and `= null` initialisers. If the tests stay green, you didn't need it |

### Default capacity, overridable

```java
public class Airport {
    private static final int DEFAULT_CAPACITY = 6;

    public Airport(Weather weather) {
        this(weather, DEFAULT_CAPACITY);
    }

    public Airport(Weather weather, int capacity) {
        // assign final fields
    }
}
```

## Before you submit
- [ ] Tests green, coverage above 95%
- [ ] No commented-out code
- [ ] README written for a stranger
- [ ] Every class has one clear responsibility
- [ ] Partial work is still committed