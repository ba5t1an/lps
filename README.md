# lps
lps is a web platform to automatically create shift schedules. The creating of the schedules is based on linear programming methods (https://en.wikipedia.org/wiki/Integer_programming) and uses Google's OR-Tools to solve the respective models. The project is currently postponed but will be continued in the future. Hence, it's currently only uploaded as showcase for Java related programming skills. The model generation is basically following the composite pattern (https://en.wikipedia.org/wiki/Composite_pattern). 

The program will run two different MILP models: The first is a knapsack model to reduce the amount of variables for the finale model. This is important due to the NP-hardness of the problem (binary variables to model XOR decisions). 

## Current status

The web platform should use Spring Boot and JSF to give people the opportunity to easily solve such problems. Currently, Spring Boot is configured with JSF but implementation of the front end is missing. The program is still executeable on jar/command-line level. The input is XML and will be transformed into the respective instances using JAXB.
