package com.exceptions;

public class NoAccess extends Exception {
    public NoAccess() { super("У вас недостаточно прав на соврение операции"); }
}
