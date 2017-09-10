## Logical Interpreter
This is a logical interpreter like prolog, but did it for
a practical work at FIUBA. The idea was understand the
functional  paradigm and learn to design applications under
that paradigm.


### Basic Flow
- Validate the database
- Validate the query
- Clean the database -> separate facts and rules
- Load rules with facts that must accomplish
- Evaluate query as a rule
- Evaluate query as a fact
- Return by or

### Test
#### Mandatories
- [x] incomplete-database-test
- [x] number-database-test
- [x] parent-database-test

#### Mine
- [x] database-test
- [x] fact-rule-test
- [x] basic-test
- [x] reader-test


### To Do List
- [ ] [Refactor] move utilities functions to other ns
- [ ] [Task] measure test coverage
- [ ] [Improvment] Give more abstraction to rules and facts
