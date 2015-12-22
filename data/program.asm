%include "asm_io.inc"
global asm_main
section .data
data1: db " Hello",10,0
data2: db " World",10,0
section .bss
section .text

asm_main:
  enter 0, 0
  pusha
  
mov eax, dword data1
call print_string
call print_nl
END: ; end prog
  popa
  leave
  ret  
