%include "asm_io.inc"
global asm_main
section .data
data: db "Hello world",10,0
i: dd 7
j: dd 2
k: dd 11
l: dd 3
section .bss
section .text

asm_main:
  enter 0, 0
  pusha
  
mov eax, dword [k]
add eax, dword [l]
add eax, dword [j]
add eax, dword [i]
mov [i], eax
mov eax, dword [i]
call print_int
call print_nl
mov eax, dword data
call print_string
call print_nl
END: ; end prog
  popa
  leave
  ret  
