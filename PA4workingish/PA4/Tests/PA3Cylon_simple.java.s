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

	#### if statement

	#### short-circuited && operation
	# &&: left operand

	### MeggyCheckButton
	call	_Z16CheckButtonsDownv
	lds	r24, Button_Up
	tst	r24
	breq	MJ_L8
MJ_L9:
	ldi	r24, 1
	jmp	MJ_L10
MJ_L8:
MJ_L10:
	push	r24

	# &&: if left operand is false do not eval right
	pop	r24
	push	r24
	# compare left exp with zero
	ldi	r25, 0
	cp    r24, r25
	# Want this, breq MJ_L6
	brne	MJ_L7
	jmp	MJ_L6

MJ_L7:
	# &&: right operand
	pop	r24

	### MeggyCheckButton
	call	_Z16CheckButtonsDownv
	lds	r24, Button_Right
	tst	r24
	breq	MJ_L11
MJ_L12:
	ldi	r24, 1
	jmp	MJ_L13
MJ_L11:
MJ_L13:
	push	r24

	# not operation
	pop    r24
	ldi     r22, 1
	eor     r24,r22
	push   r24
	pop	r24
	push   r24

MJ_L6:

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L14
	brne   MJ_L3
	jmp    MJ_L4

	#then label for if
MJ_L3:

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

	jmp	MJ_L5

	# else label for if
MJ_L4:

	# load int 5
	ldi r24,lo8(5)
	ldi r25,hi8(5)
	# push int 5 onto stack
	push r25
	push r24

	### Meggy.delay() call
	pop    r24
	pop    r25
	call   _Z8delay_msj

	jmp	MJ_L5

	# done label for if
MJ_L5:

	# load int 1000
	ldi r24,lo8(1000)
	ldi r25,hi8(1000)
	# push int 1000 onto stack
	push r25
	push r24

	### Meggy.delay() call
	pop    r24
	pop    r25
	call   _Z8delay_msj

	#jump to while test
	jmp	MJ_L0

	# end of while
MJ_L2:
/* epilogue start */
	endLabel:
	jmp endLabel
	ret
	.size   main, .-main
