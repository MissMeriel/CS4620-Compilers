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

	#### if statement

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

	/* pop args off of stack */
	pop r20
	pop r22
	call _Z6ReadPxhh
	push r24

	/* load 5 for Meggy.Color.BLUE */
	ldi r22, 5
	push r22

	# equality check expression
	pop    r18
	pop    r24
	cp    r24, r18
	breq MJ_L4

	#result is false
MJ_L3:
	ldi     r24, 0
	jmp      MJ_L5

	# result is true
MJ_L4:
	ldi     r24, 1

	# store result of equal expression
MJ_L5:
	push   r24

	# not operation
	pop    r24
	ldi     r22, 1
	eor     r24,r22
	push   r24
	pop	r24
	push   r24

	# load condition and branch if false
	pop    r24
	#load zero into reg
	ldi   r25, 0

	#use cp to set SREG
	cp     r24, r25
	#WANT breq MJ_L6
	brne   MJ_L0
	jmp    MJ_L1

	#then label for if
MJ_L0:

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

	jmp	MJ_L2

	# else label for if
MJ_L1:

	jmp	MJ_L2

	# done label for if
MJ_L2:
/* epilogue start */
	endLabel:
	jmp endLabel
	ret
	.size   main, .-main
