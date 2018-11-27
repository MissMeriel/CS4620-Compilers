	.file  "main.java"
__SREG__ = 0x3f
__SP_H__ = 0x3e
__SP_L__ = 0x3d
__tmp_reg__ = 0
__zero_reg__ = 1
	.global __do_copy_data
	.global __do_clear_bss
	.text
.global main
	.type   main, @function
main:
	push r29
	push r28
	in r28,__SP_L__
	in r29,__SP_H__
/* prologue: function */
	call _Z18MeggyJrSimpleSetupv

	# NewExp
	ldi    r24, lo8(0)
	ldi    r25, lo8(0)
	# allocating object of size 0 on heap
	call    malloc
	# push object address
	# push two byte expression onto stack
	push   r25
	push   r24

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_run
/* epilogue start */
	endLabel:
	jmp endLabel
	ret
	.size   main, .-main

	.text
.global Solver_initMaze1
	.type  Solver_initMaze1, @function
Solver_initMaze1:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r23
	std    Y + 1, r22

/* done with function Solver_initMaze1 prologue */

/* epilogue start for  Solver_initMaze1*/
	# no return value
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_initMaze1, .-Solver_initMaze1

	.text
.global Solver_initMazeCool
	.type  Solver_initMazeCool, @function
Solver_initMazeCool:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r23
	std    Y + 1, r22

/* done with function Solver_initMazeCool prologue */
	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 7
	ldi r24,lo8(7)
	ldi r25,hi8(7)
	# push int 7 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 7
	ldi r24,lo8(7)
	ldi r25,hi8(7)
	# push int 7 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_mazeRow

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 7
	ldi r24,lo8(7)
	ldi r25,hi8(7)
	# push int 7 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_mazeRow

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# load int 7
	ldi r24,lo8(7)
	ldi r25,hi8(7)
	# push int 7 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 7
	ldi r24,lo8(7)
	ldi r25,hi8(7)
	# push int 7 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_mazeCol

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 7
	ldi r24,lo8(7)
	ldi r25,hi8(7)
	# push int 7 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_mazeCol

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# load int 2
	ldi r24,lo8(2)
	ldi r25,hi8(2)
	# push int 2 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 5
	ldi r24,lo8(5)
	ldi r25,hi8(5)
	# push int 5 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 5
	ldi r24,lo8(5)
	ldi r25,hi8(5)
	# push int 5 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_mazeRow

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# load int 2
	ldi r24,lo8(2)
	ldi r25,hi8(2)
	# push int 2 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 5
	ldi r24,lo8(5)
	ldi r25,hi8(5)
	# push int 5 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 2
	ldi r24,lo8(2)
	ldi r25,hi8(2)
	# push int 2 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_mazeCol

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# load int 2
	ldi r24,lo8(2)
	ldi r25,hi8(2)
	# push int 2 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 2
	ldi r24,lo8(2)
	ldi r25,hi8(2)
	# push int 2 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 5
	ldi r24,lo8(5)
	ldi r25,hi8(5)
	# push int 5 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_mazeRow

	# load int 5
	ldi r24,lo8(5)
	ldi r25,hi8(5)
	# push int 5 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load 6 for Meggy.Color.VIOLET */
	ldi r22, 6
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev


/* epilogue start for  Solver_initMazeCool*/
	# no return value
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_initMazeCool, .-Solver_initMazeCool

	.text
.global Solver_run
	.type  Solver_run, @function
Solver_run:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r23
	std    Y + 1, r22

/* done with function Solver_run prologue */
	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_initMazeCool

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 6
	ldi r24,lo8(6)
	ldi r25,hi8(6)
	# push int 6 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 6
	ldi r24,lo8(6)
	ldi r25,hi8(6)
	# push int 6 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_move


/* epilogue start for  Solver_run*/
	# no return value
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_run, .-Solver_run

	.text
.global Solver_inBounds
	.type  Solver_inBounds, @function
Solver_inBounds:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r27
	std    Y + 1, r26
	std    Y + 3, r25
	std    Y + 4, r24

/* done with function Solver_inBounds prologue */
	#### short-circuited && operation
	# &&: left operand

	#### short-circuited && operation
	# &&: left operand

	#### short-circuited && operation
	# &&: left operand

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	#do INT sub operation
	sub    r24, r18
	sbc    r25, r19
	# push hi order byte first
	# push two byte expression onto stack
	push   r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# IdExp
	# load value for variable x
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# less than expression
	# load a one byte expression off stack
	pop    r18
	pop    r24
	cp    r24, r18
	brlt MJ_L7

	# load false
MJ_L6:
	ldi     r24, 0
	jmp      MJ_L8
	
	# load true
MJ_L7:
	ldi    r24, 1
	
	# push result of less than
	
	# push one byte expression onto stack
	push   r24

	# &&: if left operand is false do not eval right
	pop	r24
	push	r24
	# compare left exp with zero
	ldi	r25, 0
	cp    r24, r25
	# Want this, breq MJ_L4
	brne	MJ_L5
	jmp	MJ_L4

MJ_L5:
	# &&: right operand
	pop	r24

	# IdExp
	# load value for variable x
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# load int 8
	ldi r24,lo8(8)
	ldi r25,hi8(8)
	# push int 8 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# less than expression
	# load a one byte expression off stack
	pop    r18
	pop    r24
	cp    r24, r18
	brlt MJ_L9

	# load false
MJ_L8:
	ldi     r24, 0
	jmp      MJ_L10
	
	# load true
MJ_L9:
	ldi    r24, 1
	
	# push result of less than
	
	# push one byte expression onto stack
	push   r24

MJ_L4:

	# &&: if left operand is false do not eval right
	pop	r24
	push	r24
	# compare left exp with zero
	ldi	r25, 0
	cp    r24, r25
	# Want this, breq MJ_L2
	brne	MJ_L3
	jmp	MJ_L2

MJ_L3:
	# &&: right operand
	pop	r24

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	#do INT sub operation
	sub    r24, r18
	sbc    r25, r19
	# push hi order byte first
	# push two byte expression onto stack
	push   r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# IdExp
	# load value for variable y
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# less than expression
	# load a one byte expression off stack
	pop    r18
	pop    r24
	cp    r24, r18
	brlt MJ_L11

	# load false
MJ_L10:
	ldi     r24, 0
	jmp      MJ_L12
	
	# load true
MJ_L11:
	ldi    r24, 1
	
	# push result of less than
	
	# push one byte expression onto stack
	push   r24

MJ_L2:

	# &&: if left operand is false do not eval right
	pop	r24
	push	r24
	# compare left exp with zero
	ldi	r25, 0
	cp    r24, r25
	# Want this, breq MJ_L0
	brne	MJ_L1
	jmp	MJ_L0

MJ_L1:
	# &&: right operand
	pop	r24

	# IdExp
	# load value for variable y
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# load int 8
	ldi r24,lo8(8)
	ldi r25,hi8(8)
	# push int 8 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# less than expression
	# load a one byte expression off stack
	pop    r18
	pop    r24
	cp    r24, r18
	brlt MJ_L13

	# load false
MJ_L12:
	ldi     r24, 0
	jmp      MJ_L14
	
	# load true
MJ_L13:
	ldi    r24, 1
	
	# push result of less than
	
	# push one byte expression onto stack
	push   r24

MJ_L0:


/* epilogue start for  Solver_inBounds*/
	# handle return value
	# promoting a byte to an int
	# load a one byte expression off stack
	pop    r24
	# promoting a byte to an int
	tst     r24
	brlt     MJ_L14
	ldi    r25, 0
	jmp    MJ_L15
MJ_L14
	ldi    r25, hi8(-1)
MJ_L15
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_inBounds, .-Solver_inBounds

	.text
.global Solver_isDark
	.type  Solver_isDark, @function
Solver_isDark:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r27
	std    Y + 1, r26
	std    Y + 3, r25
	std    Y + 4, r24

/* done with function Solver_isDark prologue */
	#### short-circuited && operation
	# &&: left operand

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_inBounds
	# handle return value
	# push one byte expression onto stack
	push	r24

	# &&: if left operand is false do not eval right
	pop	r24
	push	r24
	# compare left exp with zero
	ldi	r25, 0
	cp    r24, r25
	# Want this, breq MJ_L16
	brne	MJ_L17
	jmp	MJ_L16

MJ_L17:
	# &&: right operand
	pop	r24

	# IdExp
	# load value for variable x
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable y
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	### Meggy.getPixel(x,y) call
	/* pop args off of stack */
	pop r22
	pop r24
	call _Z6ReadPxhh
	push r24

	/* load 0 for Meggy.Color.DARK */
	ldi r22, 0
	push r22

	# equality check expression
	pop    r18
	pop    r24
	cp    r24, r18
	breq MJ_L19

	#result is false
MJ_L18:
	ldi     r24, 0
	jmp      MJ_L20

	# result is true
MJ_L19:
	ldi     r24, 1

	# store result of equal expression
MJ_L20:
	push   r24

MJ_L16:


/* epilogue start for  Solver_isDark*/
	# handle return value
	# promoting a byte to an int
	# load a one byte expression off stack
	pop    r24
	# promoting a byte to an int
	tst     r24
	brlt     MJ_L21
	ldi    r25, 0
	jmp    MJ_L22
MJ_L21
	ldi    r25, hi8(-1)
MJ_L22
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_isDark, .-Solver_isDark

	.text
.global Solver_isBLUE
	.type  Solver_isBLUE, @function
Solver_isBLUE:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r27
	std    Y + 1, r26
	std    Y + 3, r25
	std    Y + 4, r24

/* done with function Solver_isBLUE prologue */
	#### short-circuited && operation
	# &&: left operand

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_inBounds
	# handle return value
	# push one byte expression onto stack
	push	r24

	# &&: if left operand is false do not eval right
	pop	r24
	push	r24
	# compare left exp with zero
	ldi	r25, 0
	cp    r24, r25
	# Want this, breq MJ_L23
	brne	MJ_L24
	jmp	MJ_L23

MJ_L24:
	# &&: right operand
	pop	r24

	# IdExp
	# load value for variable x
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable y
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	### Meggy.getPixel(x,y) call
	/* pop args off of stack */
	pop r22
	pop r24
	call _Z6ReadPxhh
	push r24

	/* load 5 for Meggy.Color.BLUE */
	ldi r22, 5
	push r22

	# equality check expression
	pop    r18
	pop    r24
	cp    r24, r18
	breq MJ_L26

	#result is false
MJ_L25:
	ldi     r24, 0
	jmp      MJ_L27

	# result is true
MJ_L26:
	ldi     r24, 1

	# store result of equal expression
MJ_L27:
	push   r24

MJ_L23:


/* epilogue start for  Solver_isBLUE*/
	# handle return value
	# promoting a byte to an int
	# load a one byte expression off stack
	pop    r24
	# promoting a byte to an int
	tst     r24
	brlt     MJ_L28
	ldi    r25, 0
	jmp    MJ_L29
MJ_L28
	ldi    r25, hi8(-1)
MJ_L29
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_isBLUE, .-Solver_isBLUE

	.text
.global Solver_findTargetOrMove
	.type  Solver_findTargetOrMove, @function
Solver_findTargetOrMove:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	push   r30
	push   r30
	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r31
	std    Y + 1, r30
	std    Y + 3, r29
	std    Y + 4, r28
	std    Y + 5, r27
	std    Y + 6, r26

/* done with function Solver_findTargetOrMove prologue */
	#### if statement

	#### short-circuited && operation
	# &&: left operand

	# IdExp
	# load value for variable nextX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# equality check expression
	pop    r18
	pop    r24
	cp    r24, r18
	breq MJ_L36

	#result is false
MJ_L35:
	ldi     r24, 0
	jmp      MJ_L37

	# result is true
MJ_L36:
	ldi     r24, 1

	# store result of equal expression
MJ_L37:
	push   r24

	# &&: if left operand is false do not eval right
	pop	r24
	push	r24
	# compare left exp with zero
	ldi	r25, 0
	cp    r24, r25
	# Want this, breq MJ_L33
	brne	MJ_L34
	jmp	MJ_L33

MJ_L34:
	# &&: right operand
	pop	r24

	# IdExp
	# load value for variable nextY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# equality check expression
	pop    r18
	pop    r24
	cp    r24, r18
	breq MJ_L39

	#result is false
MJ_L38:
	ldi     r24, 0
	jmp      MJ_L40

	# result is true
MJ_L39:
	ldi     r24, 1

	# store result of equal expression
MJ_L40:
	push   r24

MJ_L33:

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L41
	brne   MJ_L30
	jmp    MJ_L31

	#then label for if
MJ_L30:

	# IdExp
	# load value for variable nextX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable nextY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	/* load 5 for Meggy.Color.BLUE */
	ldi r22, 5
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	jmp	MJ_L32

	# else label for if
MJ_L31:

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable nextX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable nextY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_move

	jmp	MJ_L32

	# done label for if
MJ_L32:


/* epilogue start for  Solver_findTargetOrMove*/
	# no return value
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_findTargetOrMove, .-Solver_findTargetOrMove

	.text
.global Solver_move
	.type  Solver_move, @function
Solver_move:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	push   r30
	push   r30
	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r31
	std    Y + 1, r30
	std    Y + 3, r29
	std    Y + 4, r28
	std    Y + 5, r27
	std    Y + 6, r26

/* done with function Solver_move prologue */
	# load int 256
	ldi r24,lo8(256)
	ldi r25,hi8(256)
	# push int 256 onto stack
	push r25
	push r24

	### Meggy.delay() call
	pop    r24
	pop    r25
	call   _Z8delay_msj

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	/* load 5 for Meggy.Color.BLUE */
	ldi r22, 5
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	#### if statement

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_isDark
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L45
	brne   MJ_L42
	jmp    MJ_L43

	#then label for if
MJ_L42:

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#do INT sub operation
	sub    r24, r18
	sbc    r25, r19
	# push hi order byte first
	# push two byte expression onto stack
	push   r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_findTargetOrMove

	jmp	MJ_L44

	# else label for if
MJ_L43:

	#### if statement

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_isDark
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L49
	brne   MJ_L46
	jmp    MJ_L47

	#then label for if
MJ_L46:

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_findTargetOrMove

	jmp	MJ_L48

	# else label for if
MJ_L47:

	#### if statement

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_isDark
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L53
	brne   MJ_L50
	jmp    MJ_L51

	#then label for if
MJ_L50:

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_findTargetOrMove

	jmp	MJ_L52

	# else label for if
MJ_L51:

	#### if statement

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_isDark
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L57
	brne   MJ_L54
	jmp    MJ_L55

	#then label for if
MJ_L54:

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#do INT sub operation
	sub    r24, r18
	sbc    r25, r19
	# push hi order byte first
	# push two byte expression onto stack
	push   r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_findTargetOrMove

	jmp	MJ_L56

	# else label for if
MJ_L55:

	#### if statement

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_isBLUE
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L61
	brne   MJ_L58
	jmp    MJ_L59

	#then label for if
MJ_L58:

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	/* load 3 for Meggy.Color.YELLOW */
	ldi r22, 3
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#do INT sub operation
	sub    r24, r18
	sbc    r25, r19
	# push hi order byte first
	# push two byte expression onto stack
	push   r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_move

	jmp	MJ_L60

	# else label for if
MJ_L59:

	#### if statement

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_isBLUE
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L65
	brne   MJ_L62
	jmp    MJ_L63

	#then label for if
MJ_L62:

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	/* load 3 for Meggy.Color.YELLOW */
	ldi r22, 3
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_move

	jmp	MJ_L64

	# else label for if
MJ_L63:

	#### if statement

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_isBLUE
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L69
	brne   MJ_L66
	jmp    MJ_L67

	#then label for if
MJ_L66:

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	/* load 3 for Meggy.Color.YELLOW */
	ldi r22, 3
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_move

	jmp	MJ_L68

	# else label for if
MJ_L67:

	#### if statement

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	#### function call
	# put parameter values into appropriate registers

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_isBLUE
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L73
	brne   MJ_L70
	jmp    MJ_L71

	#then label for if
MJ_L70:

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	/* load 3 for Meggy.Color.YELLOW */
	ldi r22, 3
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable currentX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#do INT sub operation
	sub    r24, r18
	sbc    r25, r19
	# push hi order byte first
	# push two byte expression onto stack
	push   r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# IdExp
	# load value for variable targetY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 6
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable targetX
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable currentY
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	#### function call
	# put parameter values into appropriate registers
	pop    r16
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_move

	jmp	MJ_L72

	# else label for if
MJ_L71:

	jmp	MJ_L72

	# done label for if
MJ_L72:

	jmp	MJ_L68

	# done label for if
MJ_L68:

	jmp	MJ_L64

	# done label for if
MJ_L64:

	jmp	MJ_L60

	# done label for if
MJ_L60:

	jmp	MJ_L56

	# done label for if
MJ_L56:

	jmp	MJ_L52

	# done label for if
MJ_L52:

	jmp	MJ_L48

	# done label for if
MJ_L48:

	jmp	MJ_L44

	# done label for if
MJ_L44:


/* epilogue start for  Solver_move*/
	# no return value
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_move, .-Solver_move

	.text
.global Solver_mazeRow
	.type  Solver_mazeRow, @function
Solver_mazeRow:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	push   r30
	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r29
	std    Y + 1, r28
	std    Y + 3, r27
	std    Y + 4, r26
	std    Y + 5, r25

/* done with function Solver_mazeRow prologue */
	# IdExp
	# load value for variable startcol
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable row
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	/* load 6 for Meggy.Color.VIOLET */
	ldi r22, 6
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	#### if statement

	# IdExp
	# load value for variable startcol
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable endcol
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# less than expression
	# load a one byte expression off stack
	pop    r18
	pop    r24
	cp    r24, r18
	brlt MJ_L78

	# load false
MJ_L77:
	ldi     r24, 0
	jmp      MJ_L79
	
	# load true
MJ_L78:
	ldi    r24, 1
	
	# push result of less than
	
	# push one byte expression onto stack
	push   r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L79
	brne   MJ_L74
	jmp    MJ_L75

	#then label for if
MJ_L74:

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable startcol
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# IdExp
	# load value for variable row
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable endcol
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	#### function call
	# put parameter values into appropriate registers
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_mazeRow

	jmp	MJ_L76

	# else label for if
MJ_L75:

	jmp	MJ_L76

	# done label for if
MJ_L76:


/* epilogue start for  Solver_mazeRow*/
	# no return value
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_mazeRow, .-Solver_mazeRow

	.text
.global Solver_mazeCol
	.type  Solver_mazeCol, @function
Solver_mazeCol:
	push   r29
	push   r28
	# make space for locals and params
	ldi    r30, 0

	push   r30
	push   r30
	push   r30
	push   r30
	push   r30
	
	# Copy stack pointer to frame pointer
	in     r28,__SP_L__
	in     r29,__SP_H__
	
	# save off parameters
	std    Y + 2, r29
	std    Y + 1, r28
	std    Y + 3, r27
	std    Y + 4, r26
	std    Y + 5, r25

/* done with function Solver_mazeCol prologue */
	# IdExp
	# load value for variable col
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable startrow
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	/* load 6 for Meggy.Color.VIOLET */
	ldi r22, 6
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	#### if statement

	# IdExp
	# load value for variable startrow
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable endrow
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# less than expression
	# load a one byte expression off stack
	pop    r18
	pop    r24
	cp    r24, r18
	brlt MJ_L84

	# load false
MJ_L83:
	ldi     r24, 0
	jmp      MJ_L85
	
	# load true
MJ_L84:
	ldi    r24, 1
	
	# push result of less than
	
	# push one byte expression onto stack
	push   r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L85
	brne   MJ_L80
	jmp    MJ_L81

	#then label for if
MJ_L80:

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

	# IdExp
	# load value for variable col
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 3
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable endrow
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 5
	# push one byte expression onto stack
	push	r24

	# IdExp
	# load value for variable startrow
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, Y + 4
	# push one byte expression onto stack
	push	r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r18
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Solver_mazeCol

	jmp	MJ_L82

	# else label for if
MJ_L81:

	jmp	MJ_L82

	# done label for if
MJ_L82:


/* epilogue start for  Solver_mazeCol*/
	# no return value
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	pop		r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Solver_mazeCol, .-Solver_mazeCol
