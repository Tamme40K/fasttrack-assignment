# FastTrack Holidays assignment

## Assignment context

This is intended to be a minimum viable product of the bundled fast track holiday scheduler backend and frontend.

## How to use

The boilerplate can be built and executed using the following Maven commands:

```bash
$ mvn clean install
$ mvn spring-boot:run
```
The server will run on `http://localhost:8080`.

Alternatively it can be launched as an application from an IDE like Intellij IDEA. 

The frontend can be launched as follows after navigating a terminal to the `ui` directory:
```bash
$ ng serve
```
The frontend will run on `http://localhost:4200`.

Within the frontend, make sure to use the employee id `klm567099` when adding holidays.

For the format of the start and end dates, use an Instant written like `3023-07-20T00:00:00Z`.


## Business rules for development

The following business rules apply:

  * There should be a gap of at least 3 working days between holidays.
  * A holiday must be planned at least 5 working days before the start date.
  * A holiday must be cancelled at least 5 working days before the start date.
  * Holidays must not overlap (for the sake of this assignment also not between different crew members).