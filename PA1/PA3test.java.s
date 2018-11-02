	.file "main.java"
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
/*push stack pointers*/
	push r29
	push r28
	in r28,__SP_L__
	in r29,__SP_H__
/*prologue: function */
	call _Z18MeggyJrSimpleSetupv
/*Need to call so MeggyJr lib gets set up*/

	call	_Z16CheckButtonsDownv
	lds	r24, Button_Up
	tst	r24
	breq MJ_L8
MJ_L7:
	ldi r24,1
	jmp MJ_L8
	#brne	button_is_down
	#jmp	button_is_down
MJ_L8:
	# push one byte expression onto stack
	push r24
	# load condition and branch if false
	# load a one byte expression
	pop r24
	#load zero into reg
	ldi r25,0
	#use cp to set SREG
	cp r24, r25
	brne MJ_L4
	jmp MJ_L3
	# then label for if
MJ_L4:
	# then body …
	jmp MJ_L5
	# else label for if
MJ_L3:
	# done label for if
MJ_L5:


#button_is_up:
	#load int 3
	ldi r24,lo8(3)
	ldi r25,hi8(3)

	push r24
	push r25

	#cast int 3 to byte
	#pop r25
	#pop r24
	#push r24
	
	#load int 4*/
	ldi	r24,lo8(4)
	ldi r25,hi8(4)

	#push 2byte exp onto stack*/
	push r24
	push r25

	#add ints 3 + 4
	pop	r24
	pop r25
	add r24,r25

	#cast int 4 to byte*/
	#pop r25
	#pop r24
	#push r24

	#load 5 for BLUE*/
	ldi	r22,5
	#push 1 byte expr onto stack
	push	r22	
	
	### Meggy.setPixel(x,y,color) call
	#Draw pixel(s), display slate*/
	pop	r20
	pop r22
	pop r24
	call _Z6DrawPxhhh
	call _Z12DisplaySlatev
end_if:

done_if:



button_is_down:
     #load int 3
     ldi r24,lo8(3)
     ldi r25,hi8(3)
 
     push r24
     push r25
 
     #load int 4*/
     ldi r24,lo8(4)
     ldi r25,hi8(4)
 
     #push 2byte exp onto stack*/
     push r24
     push r25
 
     #cast int 4 to byte*/
     pop r25
     pop r24
     push r24
 
     #load 5 for BLUE*/
     ldi r22,1
     #push 1 byte expr onto stack
     push    r22
 
     ### Meggy.setPixel(x,y,color) call
     #Draw pixel(s), display slate*/
     pop r22
     pop r20
     pop r24
     call _Z6DrawPxhhh
     call _Z12DisplaySlatev

/* epilogue start */
	endLabel:
	jmp endLabel
	ret
	.size	main, .-main
