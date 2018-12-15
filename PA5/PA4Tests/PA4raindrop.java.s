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
	/* Need to call this so that the meggy library gets set up */

	#### while statement
MJ_L0:

	# True/1 expression
	ldi    r22, 1
	push	 r22

	# if not(condition)
	pop    r24
	ldi    r25,0
	cp     r24, r25
	# WANT breq MJ_L2
	brne   MJ_L1
	jmp		MJ_L2

	# while loop body
MJ_L1:

	# NewExp
	ldi    r24, lo8(0)
	ldi    r25, lo8(0)
	# allocating object of size 0 on heap
	call    malloc
	# push object address
	# push two byte expression onto stack
	push   r25
	push   r24

	# load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)
	# push int 3 onto stack
	push r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	# load int 7
	ldi r24,lo8(7)
	ldi r25,hi8(7)
	# push int 7 onto stack
	push r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24
	pop    r25

	call    Cloud_rain

	# load int 500
	ldi r24,lo8(500)
	ldi r25,hi8(500)
	# push int 500 onto stack
	push r25
	push r24

	### Meggy.delay() call
	pop    r24
	pop    r25
	call   _Z8delay_msj

	# load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)
	# push int 3 onto stack
	push r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	/* load 0 for Meggy.Color.DARK */
	ldi r22, 0
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	# load int 6
	ldi r24,lo8(6)
	ldi r25,hi8(6)
	# push int 6 onto stack
	push r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	/* load 2 for Meggy.Color.ORANGE */
	ldi r22, 2
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	#jump to while test
	jmp	MJ_L0

	# end of while
MJ_L2:

/* epilogue start */
	endLabel:
	jmp endLabel
	ret
	.size   main, .-main


	.text
.global Cloud_rain
	.type  Cloud_rain, @function
Cloud_rain:
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
	std    Y + 2, r25
	std    Y + 1, r24
	std    Y + 3, r22
	std    Y + 4, r20

/* done with function Cloud_rain prologue */
	#### if statement

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

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

	#### function call
	# put parameter values into appropriate registers

	pop    r20

	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Cloud_inBounds
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L3
	brne   MJ_L4
	jmp    MJ_L3

	#then label for if
MJ_L4:

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

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24
 # load a one byte expression off stack
	pop    r18
	# promoting a byte to an int
	tst     r18
	brlt     MJ_L9
	ldi    r19, 0
	jmp    MJ_L10
MJ_L9:
	ldi    r19, hi8(-1)
MJ_L10:
 # load a one byte expression off stack
	pop    r24
	# promoting a byte to an int
	tst     r24
	brlt     MJ_L11
	ldi    r25, 0
	jmp    MJ_L12
MJ_L11:
	ldi    r25, hi8(-1)
MJ_L12:

	#do add operation
	add    r24, r18
	adc    r25, r19
	# push two byte expression onto stack
	push   r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	#### function call
	# put parameter values into appropriate registers

	pop    r20

	pop    r22

	# receiver will be passed as first param
	pop    r24

	pop    r25

	call    Cloud_inBounds
	# handle return value
	# push one byte expression onto stack
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L6
	brne   MJ_L7
	jmp    MJ_L6

	#then label for if
MJ_L7:

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

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24
 # load a one byte expression off stack
	pop    r18
	# promoting a byte to an int
	tst     r18
	brlt     MJ_L13
	ldi    r19, 0
	jmp    MJ_L14
MJ_L13:
	ldi    r19, hi8(-1)
MJ_L14:
 # load a one byte expression off stack
	pop    r24
	# promoting a byte to an int
	tst     r24
	brlt     MJ_L15
	ldi    r25, 0
	jmp    MJ_L16
MJ_L15:
	ldi    r25, hi8(-1)
MJ_L16:

	#do add operation
	add    r24, r18
	adc    r25, r19
	# push two byte expression onto stack
	push   r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	/* load 0 for Meggy.Color.DARK */
	ldi r22, 0
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	jmp	MJ_L8

	# else label for if
MJ_L6:

	# done label for if
MJ_L8:

	# load int 100
	ldi r24,lo8(100)
	ldi r25,hi8(100)
	# push int 100 onto stack
	push r25
	push r24

	### Meggy.delay() call
	pop    r24
	pop    r25
	call   _Z8delay_msj

	# loading the implicit "this"
	# load a two byte variable from base+offset
	ldd    r31, Y + 2
	ldd    r30, Y + 1
	# push two byte expression onto stack
	push 	   r31
	push     r30

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

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24
 # load a one byte expression off stack
	pop    r18
 # load a one byte expression off stack
	pop    r24
	# promoting a byte to an int
	tst     r24
	brlt     MJ_L17
	ldi    r25, 0
	jmp    MJ_L18
MJ_L17:
	ldi    r25, hi8(-1)
MJ_L18:
	# promoting a byte to an int
	tst     r18
	brlt     MJ_L19
	ldi    r19, 0
	jmp    MJ_L20
MJ_L19:
	ldi    r19, hi8(-1)
MJ_L20:

	#do INT sub operation
	sub    r24, r18
	sbc    r25, r19
	# push hi order byte first
	# push two byte expression onto stack
	push   r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	#### function call
	# put parameter values into appropriate registers
	pop    r20
	pop    r22

	# receiver will be passed as first param
	pop    r24
	pop    r25

	call    Cloud_rain

	jmp	MJ_L5

	# else label for if
MJ_L3:

	# done label for if
MJ_L5:


/* epilogue start for  Cloud_rain*/
	# no return value
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30

	pop    r30
	pop    r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Cloud_rain, .-Cloud_rain

	.text
.global Cloud_inBounds
	.type  Cloud_inBounds, @function
Cloud_inBounds:
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
	std    Y + 2, r25
	std    Y + 1, r24
	std    Y + 3, r22
	std    Y + 4, r20

/* done with function Cloud_inBounds prologue */
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
 # load two byte expression off stack
	pop    r18
	pop    r19
 # load two byte expression off stack
	pop    r24
	pop    r25

	#do INT sub operation
	sub    r24, r18
	sbc    r25, r19
	# push hi order byte first
	# push two byte expression onto stack
	push   r25
	push r24

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
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
	brlt MJ_L24

	# load false
MJ_L23:
	ldi     r24, 0
	jmp      MJ_L25
	
	# load true
MJ_L24:
	ldi    r24, 1

	# push result of less than
MJ_L25:
	# push one byte expression onto stack
	push   r24

	# &&: if left operand is false do not eval right
	pop	r24
	push	r24
	# compare left exp with zero
	ldi	r25, 0
	cp    r24, r25
	# Want this, breq MJ_L21
	brne	MJ_L22
	jmp	MJ_L21

MJ_L22:
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

	# Casting int to byte by popping
	# 2 bytes off stack and only pushing low order bits
	# back on.  Low order bits are on top of stack.
	pop r24
	pop r25
	push r24

	# less than expression
	# load a one byte expression off stack
	pop    r18
	pop    r24
	cp    r24, r18
	brlt MJ_L27

	# load false
MJ_L26:
	ldi     r24, 0
	jmp      MJ_L28
	
	# load true
MJ_L27:
	ldi    r24, 1

	# push result of less than
MJ_L28:
	# push one byte expression onto stack
	push   r24

MJ_L21:


/* epilogue start for  Cloud_inBounds*/
	# handle return value
	# promoting a byte to an int
	# load a one byte expression off stack
	pop    r24
	# promoting a byte to an int
	tst     r24
	brlt     MJ_L29
	ldi    r25, 0
	jmp    MJ_L30
MJ_L29:
	ldi    r25, hi8(-1)
MJ_L30:
	# pop space off stack for parameters and locals
	pop		r30
	pop		r30

	pop    r30
	pop    r30
	# restoring the frame pointer
	pop    r28 
	pop    r29 
	ret
	.size Cloud_inBounds, .-Cloud_inBounds