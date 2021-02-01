package com.mazeltov.common.exception

class ProductGroupDoesNotExistException(message: String) : Exception(message)
class ProductDoesNotExistException(message: String) : Exception(message)
class GroupVariantDoesNotExistException(message: String) : Exception(message)
class UserDoesNotExistException(message: String) : Exception(message)
class ReviewDoesNotExistException(message: String) : Exception(message)
class EmptyDdExistException(message: String) : Exception(message)
class CartDoesNotExistException(message: String) : Exception(message)
class ItemNotInTheCartException(message: String) : Exception(message)
class ProductAlReadyInCartException(message: String) : Exception(message)
class OrderDoesNotExistException(message: String) : Exception(message)
class UserAlReadyExistException(message: String) : Exception(message)
class PasswordsAreNotTheSameExistException(message: String) : Exception(message)
