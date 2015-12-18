%include "asm_io.inc"
global asm_main

section .data

argErorr: db "incorrect number of command line arguments",10,0
strLenghtError: db "incorrect arg lenght (1-20)",10,0
strContentsError: db "agr must be lower-case letter",10,0

N: dd 0

section .bss
X: resb 20
Y: resd 20

section .text

asm_main:
  enter 0, 0
  pusha

  mov eax, dword [ebp+8]  ; argc  
  cmp eax, dword 2
  jne CMD_NUM_ERROR

  mov ebx, dword [ebp+12]  ; address of argv[] 
  mov ecx, dword [ebx+4]   ; get argv[1] argument -- ptr to string
  
  call START_COUNT
  push ebx ; length
  push edx
  
  call EVAL_LENGTH
  cmp edx, dword 1
  je LENGTH_ERROR
  
  pop edx
  pop ebx
  cmp edx, dword 1
  je CONTENTS_ERROR
  
  mov [N], dword ebx
  
  call STORE_ARRAY 
  mov edx, dword 1
  call DISPLAY_ARRAY  
  call CALC_LYNDON
  
  mov edx, dword 0
  call DISPLAY_ARRAY
  
  jmp END

DISPLAY_ARRAY:  
  cmp edx, dword 1
  je DISPLAY_C_ARRAY
  
  cmp edx, dword 0
  je DISPLAY_I_ARRAY  
  
DISPLAY_C_ARRAY:
  mov eax, X ;pass Y address
  push eax
  
  mov eax, [N]
  push eax
  
  mov eax, dword 1 ; set flag to print int array
  push eax
  
  call display
  add esp, dword 12; cleara wasat of space spot
  ret

DISPLAY_I_ARRAY:
  mov eax, Y ;pass Y address
  push eax
  
  mov eax, [N]
  push eax
  
  mov eax, dword 0 ; set flag to print int array
  push eax
  
  call display
  add esp, dword 12; cleara wasat of space spot
  ret

STORE_ARRAY:
  mov ebx, dword 0
  mov edx, dword 0
  push ecx
  call COPY
  pop ecx
  ret

CALC_LYNDON:
  mov ebx, dword 0
  mov edx, dword 0
LOOP_LYNDON:
  cmp edx, dword[N]
  jge RET
  
  push ebx ; i
  push X
  push dword [N]
  push edx ; k
  
  call maxLyn
  
  pop edx ; k
  add esp, dword 4; cleara wasat of space spot
  pop edi ; returned p
  mov eax, edi

  pop ebx ; i  
  
  mov ecx, Y
  add ecx, dword ebx
  mov [ecx], dword edi ; put p in array
  inc edx
  add ebx, dword 4
  jmp LOOP_LYNDON

COPY: ; count lenght of str in ecx ; loop for string check
  mov al, byte[ecx] ;Load character into ebx
  inc ecx
  
  cmp al, 0 ;Check for NULL Character
  je RET
  
  cmp edx, dword 20 ;Only reserved 20 spaces
  jge RET
  
  mov ebx, X ;Move address X -> ecx
  add ebx, edx
  mov [ebx], al ;X[i] = byte[ebx]
  
  inc edx ;Move to next position.
  jmp COPY

START_COUNT: ; run the string to check length and get letter details
  mov ebx, dword 0
  mov edx, dword 0
  push ecx
  call COUNT
  pop ecx
  ret

COUNT: ; count lenght of str in ecx ; loop for string check
  mov al, byte[ecx]
  inc ecx
  
  cmp al, 0
  je RET
  
  call CHECK_LETTER
  mov eax, dword 1 ; remove probaly
  inc ebx
  jne COUNT
  
CHECK_LETTER: ; that char is a lower-case letter
  mov dl, byte 'a'   
  cmp al, dl
  jb INVALIDATE
  
  mov dl, byte 'z'   
  cmp al, dl
  jg INVALIDATE

  ret
  
EVAL_LENGTH: ; make sure that the input lenght n is 1<= n <= 20
  mov edx, dword 0
  
  cmp ebx, dword 1
  jl INVALIDATE
  
  cmp ebx, dword 20
  jg INVALIDATE
  
  ret
  
INVALIDATE: ; set invalidate flag
  mov edx, dword 1
  ret

RET: ; returns to last caller
  ret

CMD_NUM_ERROR: ; print error for wrong arg num
  mov eax, dword argErorr
  call print_string
  call print_nl
  jmp END
  
print_space: ; print error for wrong arg num
  mov al, byte ' '
  call print_char
  ret
  
LENGTH_ERROR:
  pop edx ; remove values pushed to stack. not sure why popa doesnt do this
  pop eax ; but with out it you get a seg fault
  mov eax, dword strLenghtError
  call print_string
  call print_nl
  jmp END
  
CONTENTS_ERROR:
  mov eax, dword strContentsError
  call print_string
  call print_nl
  jmp END
  
END: ; end prog
  popa
  leave
  ret  
  
;-------------------------------------------------------------------

display: 
  ; (Z, n, flag) -> void
    ; Z address of data array
    ; n lenght of array
    ; flag, if 1 data is of chars else if 0 data is of int
  
  enter 0, 0
  pusha  

  mov edx, [ebp+8]   ; flag to decide print type
  mov ecx, [ebp+12]   ; size
  mov ebx, [ebp+16]   ; data  
  mov eax, edx
  mov edx, dword 0
  
  cmp eax, dword 1
  je PRINT_ARR_C
  
  cmp eax, dword 0
  je PRINT_ARR_I
  
  PRINT_ARR_C:
    cmp ecx, dword 0 
    jbe END_display 
    
    mov al, byte[ebx] 
    call print_char 
    
    add ebx, dword 1 
    sub ecx, dword 1 
    jmp PRINT_ARR_C
  
  PRINT_ARR_I: 
    cmp ecx, dword 0 
    jbe END_display 
    
    mov eax, [ebx] 
    call print_int 
    call print_space
    
    add ebx, dword 4 
    sub ecx, dword 1 
    jmp PRINT_ARR_I 
    
  END_display:
    call read_char
    leave
    ret

;----------------------------------------------------------------------------

maxLyn: 
   ; (Z, n, slit) -> void
     ; Z address of data array
     ; n lenght of array
     ; split point
   
  enter 0, 0
  pusha  
  
  mov esi, [ebp+8]   ; split k
  mov edi, [ebp+12]   ; size n
  mov edx, [ebp+16]   ; data
   
  mov ecx, esi
  add ecx, dword 1 ; i =k + 1
  mov esi, dword 1 ; p = 1
   
  LOOP:
    mov edx, [ebp+16]   ; restor data
    
    cmp ecx, edi ; break if i >= n
      jge END_maxLyn
    
    add edx, ecx
    mov bl, byte[edx]
    sub edx, esi
    
    cmp byte[edx], bl
      jne CHECK
    
    jmp NEXT
    
    
  CHECK:
    cmp byte[edx], bl
      jg END_maxLyn
    mov esi, ecx
    inc esi
    sub esi, [ebp+8]
    jmp NEXT      
  
  
  NEXT:
    inc ecx
      jmp LOOP 
    
   END_maxLyn:
    mov [ebp+16], esi ; return esi
    leave
    ret

;---------------------------------------------------------------