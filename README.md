# Tracy 
Tracing facts over Knowledge Graphs and text.

This is the Web user interface for [*ExFaKT*](https://github.com/mhmgad/ExFaKT) framework.

## Run 

1. Run [ExFaKT](https://github.com/mhmgad/ExFaKT) system first (do not forget to start elasticsearch)
2. Fix the host and ports of ExFaKT and elasticsearch in file [fact_checking_demo/conf/demo.properties]
3. Change port number for the run comman `./sbt "run 9400"` if necessary.
4. Run `./sbt "run 9400"`

## Reference
This is the implementation for the paper:

*Tracy: Tracing Facts over Knowledge Graphs and Text (Demo)*
Mohamed Gad-Elrab, Daria Stepanova, Jacopo Urbani, Gerhard Weikum
In The Web Conference (WWW'19), ACM 2019.
