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

	# load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)
	# push int 3 onto stack
	push r25
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load 5 for Meggy.Color.BLUE */
	ldi r22, 5
	push r22

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
/* epilogue start */
	endLabel:
	jmp endLabel
	ret
	.size   main, .-main

	.text
.global C_setP
	.type  C_setP, @function
C_setP:
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
	
	# save off parametersstd    Y + 2, r27
std    Y + 1, r26

	std    Y + 3, r25

	std    Y + 4, r24

	pop    r23

/* done with function C_setP prologue */
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

	# IdExp
	# load value for variable c
	# variable is a local or param variable

	# load a one byte variable from base+offset
	ldd    r24, INVALID + 0
	# push one byte expression onto stack
	push	r24

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

/* epilogue start for Cloud_rain */
	# no return value
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
	.size C_setP, .-C_setP
