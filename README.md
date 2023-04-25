videostore
==========

The videostore example from Martin Fowler's Refactoring, and from Episode 3 of cleancoders.com

### Guidelines

* (re)run tests after every single refactoring, even if it's just moving lines around (ctrl+F5)
* commit often, or rollback using the 'local history' from the right-click menu. This menu marks your test successes/failures if you remembered to run them often!
* avoid manual actions (copy-di-pasta isa bad, mkay? 🤌). Make your IDE do the work

### Target refactorings

* (optional) mavenise the project
* apply proper formatting
* bump junit version to 4 (https://stackoverflow.com/questions/264680/best-way-to-automagically-migrate-tests-from-junit-3-to-junit-4)
* bump again to junit 5 (magical refactoring...)
* (optional) replace junit assertions with beautiful assertj assertions (inspections.xml)
* rework tests so they test behaviour rather than String outputs
  * don't be afraid to expose data to make it testable: [*"Tests trump encapsulation"*](https://twitter.com/unclebobmartin/status/43581463823777792?lang=en) - Robert C. Martin
