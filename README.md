## DCLBOX Nicknames

The `Number.DCLBOX()` method renders `Number` object to show the full superposition for debugging during development.  Only 2x2 Sudoku puzzles are supported. (512 separate glyphs would be necessary for a 3x3)

This table shows what the different symbols mean.  The letters A, B, C, and D correspond to the numbers 1 through 4, and mean that that number alone has been eliminated.  

| 1 | 2 | 3 | 4 |Symbol|Notes|
|---|---|---|---|:----:|-----|
|Yes|Yes|Yes|Yes|   | Blank, no information |
|Yes|Yes|Yes|No | D | not 4 |
|Yes|Yes|No |Yes| C | not 3 |
|Yes|Yes|No |No | L | Low (1 or 2) |
|Yes|No |Yes|Yes| B | not 2 |
|Yes|No |Yes|No | O | Odd |
|Yes|No |No |Yes| X | eXtreme (1 or 4) |
|Yes|No |No |No | 1 | Definite |
|No |Yes|Yes|Yes| A | not 1 |
|No |Yes|Yes|No | T | starts with T (2 or 3)|
|No |Yes|No |Yes| E | Even |
|No |Yes|No |No | 2 | Definite |
|No |No |Yes|Yes| H | High (3 or 4) |
|No |No |Yes|No | 3 | Definite |
|No |No |No |Yes| 4 | Definite |
|No |No |No |No | # | Error, no solution |

(The name DCLBOX comes from the first six letters reading down the table.)