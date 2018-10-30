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

	/* load int 1*/
	ldi r24,lo8(1)
	ldi r25,hi8(1)

	/* push int 1onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load int 1*/
	ldi r24,lo8(1)
	ldi r25,hi8(1)

	/* push int 1onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load 3 for Meggy.Color.YELLOW */
	ldi r22, 3
	push r22

	/* pop args off of stack */
	pop r20
	pop r22
	pop r24

	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	/* load int 1*/
	ldi r24,lo8(1)
	ldi r25,hi8(1)

	/* push int 1onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load int 2*/
	ldi r24,lo8(2)
	ldi r25,hi8(2)

	/* push int 2onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load 2 for Meggy.Color.ORANGE */
	ldi r22, 2
	push r22

	/* pop args off of stack */
	pop r20
	pop r22
	pop r24

	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	/* load int 1*/
	ldi r24,lo8(1)
	ldi r25,hi8(1)

	/* push int 1onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load int 3*/
	ldi r24,lo8(3)
	ldi r25,hi8(3)

	/* push int 3onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load 1 for Meggy.Color.RED */
	ldi r22, 1
	push r22

	/* pop args off of stack */
	pop r20
	pop r22
	pop r24

	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	/* load int 1*/
	ldi r24,lo8(1)
	ldi r25,hi8(1)

	/* push int 1onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load int 4*/
	ldi r24,lo8(4)
	ldi r25,hi8(4)

	/* push int 4onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load 6 for Meggy.Color.VIOLET */
	ldi r22, 6
	push r22

	/* pop args off of stack */
	pop r20
	pop r22
	pop r24

	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	/* load int 1*/
	ldi r24,lo8(1)
	ldi r25,hi8(1)

	/* push int 1onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load int 5*/
	ldi r24,lo8(5)
	ldi r25,hi8(5)

	/* push int 5onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load 5 for Meggy.Color.BLUE */
	ldi r22, 5
	push r22

	/* pop args off of stack */
	pop r20
	pop r22
	pop r24

	/* Draw pixels, display slate */
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

	/* load int 1*/
	ldi r24,lo8(1)
	ldi r25,hi8(1)

	/* push int 1onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load int 6*/
	ldi r24,lo8(6)
	ldi r25,hi8(6)

	/* push int 6onto stack */
	push r24
	push r25

	/* cast to byte */
	pop r25
	pop r24
	push r24

	/* load 4 for Meggy.Color.GREEN */
	ldi r22, 4
	push r22

	/* pop args off of stack */
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

