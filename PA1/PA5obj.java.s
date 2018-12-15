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


    # NewExp
    ldi    r24, lo8(2)
    ldi    r25, hi8(2)
    # allocating object of size 2 on heap
    call    malloc
    # push object address
    # push two byte expression onto stack
    push   r25
    push   r24

    # Load constant int 3
    ldi    r24,lo8(3)
    ldi    r25,hi8(3)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Load constant int 7
    ldi    r24,lo8(7)
    ldi    r25,hi8(7)
    # push two byte expression onto stack
    push   r25
    push   r24

    # Casting int to byte by popping
    # 2 bytes off stack and only pushing low order bits
    # back on.  Low order bits are on top of stack.
    pop    r24
    pop    r25
    push   r24

    # Color expression Meggy.Color.BLUE
    ldi    r22,5
    # push one byte expression onto stack
    push   r22

    #### function call
    # put parameter values into appropriate registers
    # load a one byte expression off stack
    pop    r18
    # load a one byte expression off stack
    pop    r20
    # load a one byte expression off stack
    pop    r22
    # receiver will be passed as first param
    # load a two byte expression off stack
    pop    r24
    pop    r25

    call    C_setP


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
    push   r17
    push   r16
    push   r3
    push   r2
    push   r5
    push   r4
    push   r7
    push   r6
    push   r9
    push   r8
    push   r11
    push   r10
    push   r13
    push   r12
    push   r15
    push   r14
    # make space for locals and params
    ldi    r30, 0
    push   r30
    push   r30

    # Copy stack pointer to frame pointer
    in     r28,__SP_L__
    in     r29,__SP_H__

    # save off parameters
    std    Y + 2, r25
    std    Y + 1, r24
    # move low byte src into dest reg
    mov    r4, r22
    # move low byte src into dest reg
    mov    r2, r20
    # move low byte src into dest reg
    mov    r16, r18
/* done with function C_setP prologue */


    # NewExp
    ldi    r24, lo8(1)
    ldi    r25, hi8(1)
    # allocating object of size 1 on heap
    call    malloc
    # push object address
    # move low byte src into dest reg
    mov    r8, r24
    # move hi byte src into dest reg
    mov    r9, r25

    ### AssignStatement
    # load rhs exp
    # store rhs into var ox
    # move low byte src into dest reg
    mov    r6, r8
    # move hi byte src into dest reg
    mov    r7, r9

    # IdExp
    # load value for variable ox
    # variable is a local or param variable
    # var ox is in r6
    # move low byte src into dest reg
    mov    r8, r6
    # move hi byte src into dest reg
    mov    r9, r7

    # IdExp
    # load value for variable x
    # variable is a local or param variable
    # var x is in r4
    # move low byte src into dest reg
    mov    r10, r4

    #### function call
    # put parameter values into appropriate registers
    # move low byte src into dest reg
    mov    r22, r10
    # receiver will be passed as first param
    # move low byte src into dest reg
    mov    r24, r8
    # move hi byte src into dest reg
    mov    r25, r9

    call    Ind_put

    # NewExp
    ldi    r24, lo8(1)
    ldi    r25, hi8(1)
    # allocating object of size 1 on heap
    call    malloc
    # push object address
    # move low byte src into dest reg
    mov    r8, r24
    # move hi byte src into dest reg
    mov    r9, r25

    ### AssignStatement
    # load rhs exp

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # store rhs into var oy
    std    Z + 1, r9
    std    Z + 0, r8

    # IdExp
    # load value for variable oy

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # variable is a member variable

    # load a two byte variable from base+offset
    ldd    r25, Z + 1
    ldd    r24, Z + 0
    # move low byte src into dest reg
    mov    r8, r24
    # move hi byte src into dest reg
    mov    r9, r25

    # IdExp
    # load value for variable y
    # variable is a local or param variable
    # var y is in r2
    # move low byte src into dest reg
    mov    r10, r2

    #### function call
    # put parameter values into appropriate registers
    # move low byte src into dest reg
    mov    r22, r10
    # receiver will be passed as first param
    # move low byte src into dest reg
    mov    r24, r8
    # move hi byte src into dest reg
    mov    r25, r9

    call    Ind_put

    # IdExp
    # load value for variable ox
    # variable is a local or param variable
    # var ox is in r6
    # move low byte src into dest reg
    mov    r8, r6
    # move hi byte src into dest reg
    mov    r9, r7

    #### function call
    # put parameter values into appropriate registers
    # receiver will be passed as first param
    # move low byte src into dest reg
    mov    r24, r8
    # move hi byte src into dest reg
    mov    r25, r9

    call    Ind_get

    # handle return value
    # move low byte src into dest reg
    mov    r8, r24
    # move hi byte src into dest reg
    mov    r9, r25

    # IdExp
    # load value for variable oy

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # variable is a member variable

    # load a two byte variable from base+offset
    ldd    r25, Z + 1
    ldd    r24, Z + 0
    # move low byte src into dest reg
    mov    r10, r24
    # move hi byte src into dest reg
    mov    r11, r25

    #### function call
    # put parameter values into appropriate registers
    # receiver will be passed as first param
    # move low byte src into dest reg
    mov    r24, r10
    # move hi byte src into dest reg
    mov    r25, r11

    call    Ind_get

    # handle return value
    # move low byte src into dest reg
    mov    r10, r24
    # move hi byte src into dest reg
    mov    r11, r25

    # IdExp
    # load value for variable c
    # variable is a local or param variable
    # var c is in r16
    # move low byte src into dest reg
    mov    r12, r16

    ### Meggy.setPixel(x,y,color) call
    # move low byte src into dest reg
    mov    r20, r12
    # move low byte src into dest reg
    mov    r22, r10
    # move low byte src into dest reg
    mov    r24, r8
    call   _Z6DrawPxhhh
    call   _Z12DisplaySlatev

/* epilogue start for C_setP */
    # no return value
    # pop space off stack for parameters and locals
    pop    r30
    pop    r30
    pop   r14
    pop   r15
    pop   r12
    pop   r13
    pop   r10
    pop   r11
    pop   r8
    pop   r9
    pop   r6
    pop   r7
    pop   r4
    pop   r5
    pop   r2
    pop   r3
    pop   r16
    pop   r17
    # restoring the frame pointer
    pop    r28
    pop    r29
    ret
    .size C_setP, .-C_setP


    .text
.global Ind_put
    .type  Ind_put, @function
Ind_put:
    push   r29
    push   r28
    push   r17
    push   r16
    push   r3
    push   r2
    push   r5
    push   r4
    push   r7
    push   r6
    push   r9
    push   r8
    push   r11
    push   r10
    # make space for locals and params
    ldi    r30, 0
    push   r30
    push   r30

    # Copy stack pointer to frame pointer
    in     r28,__SP_L__
    in     r29,__SP_H__

    # save off parameters
    std    Y + 2, r25
    std    Y + 1, r24
    # move low byte src into dest reg
    mov    r16, r22
/* done with function Ind_put prologue */


    # IdExp
    # load value for variable i
    # variable is a local or param variable
    # var i is in r16
    # move low byte src into dest reg
    mov    r10, r16

    ### AssignStatement
    # load rhs exp

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # store rhs into var _i
    std    Z + 0, r10

/* epilogue start for Ind_put */
    # no return value
    # pop space off stack for parameters and locals
    pop    r30
    pop    r30
    pop   r10
    pop   r11
    pop   r8
    pop   r9
    pop   r6
    pop   r7
    pop   r4
    pop   r5
    pop   r2
    pop   r3
    pop   r16
    pop   r17
    # restoring the frame pointer
    pop    r28
    pop    r29
    ret
    .size Ind_put, .-Ind_put


    .text
.global Ind_get
    .type  Ind_get, @function
Ind_get:
    push   r29
    push   r28
    push   r17
    push   r16
    push   r3
    push   r2
    push   r5
    push   r4
    push   r7
    push   r6
    push   r9
    push   r8
    push   r11
    push   r10
    # make space for locals and params
    ldi    r30, 0
    push   r30
    push   r30

    # Copy stack pointer to frame pointer
    in     r28,__SP_L__
    in     r29,__SP_H__

    # save off parameters
    std    Y + 2, r25
    std    Y + 1, r24
/* done with function Ind_get prologue */


    # IdExp
    # load value for variable _i

    # loading the implicit "this"

    # load a two byte variable from base+offset
    ldd    r31, Y + 2
    ldd    r30, Y + 1
    # variable is a member variable

    # load a one byte variable from base+offset
    ldd    r24, Z + 0
    # move low byte src into dest reg
    mov    r8, r24
    # move hi byte src into dest reg
    mov    r9, r25

/* epilogue start for Ind_get */
    # handle return value
    # move low byte src into dest reg
    mov    r24, r8
    # move hi byte src into dest reg
    mov    r25, r9
    # promoting a byte to an int
    tst     r24
    brlt     MJ_L0
    ldi    r25, 0
    jmp    MJ_L1
MJ_L0:
    ldi    r25, hi8(-1)
MJ_L1:
    # pop space off stack for parameters and locals
    pop    r30
    pop    r30
    pop   r10
    pop   r11
    pop   r8
    pop   r9
    pop   r6
    pop   r7
    pop   r4
    pop   r5
    pop   r2
    pop   r3
    pop   r16
    pop   r17
    # restoring the frame pointer
    pop    r28
    pop    r29
    ret
    .size Ind_get, .-Ind_get

