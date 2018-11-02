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

	# load int 2
	ldi r24,lo8(2)
	ldi r25,hi8(2)
	# push int 2 onto stack
	push r25
	push r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

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

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	# load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)
	# push int 3 onto stack
	push r25
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

	# load int 2
	ldi r24,lo8(2)
	ldi r25,hi8(2)
	# push int 2 onto stack
	push r25
	push r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
	push r24

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

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	# load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)
	# push int 3 onto stack
	push r25
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

	# load int 7
	ldi r24,lo8(7)
	ldi r25,hi8(7)
	# push int 7 onto stack
	push r25
	push r24

	# load int 1
	ldi r24,lo8(1)
	ldi r25,hi8(1)
	# push int 1 onto stack
	push r25
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

	# load int 9
	ldi r24,lo8(9)
	ldi r25,hi8(9)
	# push int 9 onto stack
	push r25
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

	# load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)
	# push int 3 onto stack
	push r25
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

	/* cast to byte */
	pop r25
	pop r24
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

	/* cast to byte */
	pop r25
	pop r24
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

	# load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)
	# push int 3 onto stack
	push r25
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

	/* cast to byte */
	pop r25
	pop r24
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

	/* cast to byte */
	pop r25
	pop r24
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

	# load int 5
	ldi r24,lo8(5)
	ldi r25,hi8(5)
	# push int 5 onto stack
	push r25
	push r24

	# load int 5
	ldi r24,lo8(5)
	ldi r25,hi8(5)
	# push int 5 onto stack
	push r25
	push r24

	# load int 10
	ldi r24,lo8(10)
	ldi r25,hi8(10)
	# push int 10 onto stack
	push r25
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

	# load int 0
	ldi r24,lo8(0)
	ldi r25,hi8(0)
	# push int 0 onto stack
	push r25
	push r24

	# load int 6
	ldi r24,lo8(6)
	ldi r25,hi8(6)
	# push int 6 onto stack
	push r25
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

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* cast to byte */
	pop r25
	pop r24
	push r24

	# load int 10
	ldi r24,lo8(10)
	ldi r25,hi8(10)
	# push int 10 onto stack
	push r25
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

	# load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)
	# push int 3 onto stack
	push r25
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

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load 4 for Meggy.Color.GREEN */
	ldi r22, 4
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	# load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)
	# push int 3 onto stack
	push r25
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

	/* cast to byte */
	pop r25
	pop r24
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

	/* load 7 for Meggy.Color.WHITE */
	ldi r22, 7
	push r22

	/*MeggySetPixel: pop args off of stack */
	pop r20
	pop r22
	pop r24
	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev
/* epilogue start */
	endLabel:
	jmp endLabel
	ret
	.size   main, .-main
