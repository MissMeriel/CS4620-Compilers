	.file "PA3ifdots.java"
__SREG__ = 0x3f
__SP_H__ = 0x3e
__SP_L__ = 0x3d
__tmp_reg__=0
__zero_reg__  = 1
	.global __do_copy_data
	.global __do_clear_bss
	.text
.global main
	.type main, @function
main:
	push r29
	push r28
	in r28,__SP_L__
	in r29,__SP_H__
/*prologue: function */
	call _Z18MeggyJrSimpleSetupv

	call _Z16CheckButtonsDownv
	lds r24, Button_Up
	tst r24
	#cp r24, r25
	breq end_button_up 


button_up:
#Meggy.setPixel( (byte)3, (byte)(4+3), Meggy.Color.BLUE );
	/*load int 3*/
	ldi r24,lo8(3)
	ldi r25,hi8(3)

	/*push int onto stack*/
	push r24
	push r25

	/*cast int 3 to byte*/
	pop r25
	pop r24
	push r24
	/*byte 3 now at bottom of stack*/
	
	/*push int 3 onto stack*/
	push r24
	push r25

	/*load int 4 into register r24:r25*/
	ldi	r24,lo8(4)
	ldi r25,hi8(4)

	/*reload int 3 to reg r18:r19*/
	pop r19
	pop r18

	/*add 2byte ints into r24:r25*/
	add r24, r18
	adc r25, r19

	/*push 2byte sum onto stack*/
	push r24
	push r25

	/*cast sum to byte*/
	pop r25
	pop r24
	push r24
	#clr r24

	/*load 5 for BLUE*/
	ldi r22, 5
	push r22

	/*pop args off of stack*/
	/*N.B. Parameters start at r24, go L->R to r8*/
	pop r20
	pop r22
	pop r24

	/*Draw pixel(s), display slate*/
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev

end_button_up:
	/*check down button*/	
	call _Z16CheckButtonsDownv
	lds r24, Button_Down
	tst r24
	#cp r25,r26
	breq end_button_down

button_down:
#Meggy.setPixel( (byte)3, (byte)0, Meggy.Color.RED );	

	/*load int 3*/
	ldi r24,lo8(3)
	ldi r25,hi8(3)

	/*push int onto stack*/
	push r24
	push r25

	/*cast int 3 to byte*/
	pop r25
	pop r24
	push r24
	/*byte 3 now at bottom of stack*/
	
	/*push byte 0 onto stack*/
	push r25

	/*push 1 for RED*/	
	ldi r22, 1
	push r22
	/*current stack: TOP 5 0 3 BOT*/

	/*pop args off of stack*/
	/*N.B. Parameters start at r24, go L->R to r8*/
	pop r20
	pop r22
	pop r24

	/*Draw pixel(s), display slate*/
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev
end_button_down:
end_program:

/*epilogue start*/
	endLabel:
	jmp endLabel
	ret
	.size	main, .-main
