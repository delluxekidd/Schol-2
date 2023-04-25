#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java
java Game
