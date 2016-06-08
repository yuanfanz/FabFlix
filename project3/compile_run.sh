#!/bin/bash
cd /home/ubuntu/project3
mvn exec:java -Dexec.mainClass="source_XML_parsing.XmlParsing" -Dexec.classpathScope=runtime