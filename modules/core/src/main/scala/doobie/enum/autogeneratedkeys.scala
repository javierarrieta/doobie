// Copyright (c) 2013-2017 Rob Norris
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package doobie.enum

import doobie.util.invariant._

import java.sql.Statement._

import cats.kernel.Eq
import cats.kernel.instances.int._

/** @group Types */
sealed abstract class AutoGeneratedKeys(val toInt: Int) extends Product with Serializable

/** @group Modules */
object AutoGeneratedKeys {

  /** @group Values */ case object ReturnGeneratedKeys extends AutoGeneratedKeys(RETURN_GENERATED_KEYS)
  /** @group Values */ case object NoGeneratedKeys extends AutoGeneratedKeys(NO_GENERATED_KEYS)

  def fromInt(n: Int): Option[AutoGeneratedKeys] =
    Some(n) collect {
      case ReturnGeneratedKeys.toInt => ReturnGeneratedKeys
      case NoGeneratedKeys.toInt => NoGeneratedKeys
    }

  def unsafeFromInt(n: Int): AutoGeneratedKeys =
    fromInt(n).getOrElse(throw InvalidOrdinal[AutoGeneratedKeys](n))

  implicit val EqAutoGeneratedKeys: Eq[AutoGeneratedKeys] =
    Eq.by(_.toInt)

}
