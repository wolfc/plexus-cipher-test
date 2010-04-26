/*
 * JBoss, Home of Professional Open Source
 * Copyright (c) 2010, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.beach.plexus.components.cipher;

import org.junit.Test;
import org.sonatype.plexus.components.cipher.DefaultPlexusCipher;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class SimpleTestCase
{
   @Test
   public void test1() throws Exception
   {
      String master = "{WR51wo2sI1QHlQM0MhI7AULqJXRZvtoppQsqdg74p08=}";

      DefaultPlexusCipher cipher = new DefaultPlexusCipher();

      String result = cipher.decryptDecorated(master, "settings.security");
      assertEquals("Doh!", result);
   }

   @Test
   public void test2() throws Exception
   {
      String master = "Doh!";
      String pwd = "{fB3b7bF6RKUHOTcOH790i8jm4C4fIBM4BZ5UvXSTODk=}";

      DefaultPlexusCipher cipher = new DefaultPlexusCipher();

      String result = cipher.decryptDecorated(pwd, master);
      assertEquals("Eeek!", result);
   }
}
