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

	.text
.global Simplebluedot
	.type  Simplebluedot, @function
Simplebluedot:
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
	
	# save off parametersstd    Y + 2, r25
std    Y + 1, r24

	pop    r23

	pop    r21

/* done with function Simplebluedot prologue *//* epilogue start */
	endLabel:
	jmp endLabel
	ret
	.size   main, .-main
