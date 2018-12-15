Github username: MissMeriel
UVA email: ms7nk@virginia.edu

Simpler examples working.
Having trouble with retreiving CallExp type from MethodSTE lookup.
Handed in on 11/24 but here are additional notes on the problem I'm having. They're all more code related than they are conceptual:
- added some debug statements and the issue is that CheckTypes is somehow receiving an empty symbol table.
- symbol table scope stack and quicklookup table are properly formed when leaving BuildSymTable
