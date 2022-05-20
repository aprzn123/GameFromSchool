# Simple Dungeon Crawler
A simple, terminal-based dungeon crawler game.

Sample screencap (actual game has color):
```
Z Z Z Z Z Z Z
Z K . . . . Z
Z . . . . . Z
Z . . P . . .
Z . . . . . Z
Z . . . . . Z
Z Z Z . Z Z Z
```

For this game, I developed a useful ColoredString class, which allows me to perform most standard string operations on colored strings without having to manually account for the extra characters added by the terminal color commands. I also created a builder class to more effectively construct instances of ColoredString.
