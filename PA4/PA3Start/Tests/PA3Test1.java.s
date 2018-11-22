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

	### MeggyCheckButton
	call	_Z16CheckButtonsDownv
	lds	r24, Button_A
	tst	r24
	breq	MJ_L6
MJ_L7:
	ldi	r24, 1
	jmp	MJ_L8
MJ_L6:
MJ_L8:
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L9
	brne   MJ_L3
	jmp    MJ_L4

	#then label for if
MJ_L3:

	#### if statement

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

	/* pop args off of stack */
	pop r20
	pop r22
	call _Z6ReadPxhh
	push r24

	/* load 1 for Meggy.Color.RED */
	ldi r22, 1
	push r22

	# equality check expression
	pop    r18
	pop    r24
	cp    r24, r18
	breq MJ_L14

	#result is false
MJ_L13:
	ldi     r24, 0
	jmp      MJ_L15

	# result is true
MJ_L14:
	ldi     r24, 1

	# store result of equal expression
MJ_L15:
	push   r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L16
	brne   MJ_L10
	jmp    MJ_L11

	#then label for if
MJ_L10:

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

	jmp	MJ_L12

	# else label for if
MJ_L11:

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

	jmp	MJ_L12

	# done label for if
MJ_L12:

	jmp	MJ_L5

	# else label for if
MJ_L4:

	#### if statement

	### MeggyCheckButton
	call	_Z16CheckButtonsDownv
	lds	r24, Button_B
	tst	r24
	breq	MJ_L20
MJ_L21:
	ldi	r24, 1
	jmp	MJ_L22
MJ_L20:
MJ_L22:
	push	r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L23
	brne   MJ_L17
	jmp    MJ_L18

	#then label for if
MJ_L17:

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

	/* load 1 for Meggy.Color.RED */
	ldi r22, 1
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	jmp	MJ_L19

	# else label for if
MJ_L18:

	jmp	MJ_L19

	# done label for if
MJ_L19:

	jmp	MJ_L5

	# done label for if
MJ_L5:

	#jump to while test
	jmp	MJ_L0

	# end of while
MJ_L2:
/* epilogue start */
	endLabel:
	jmp endLabel
	ret
	.size   main, .-main
