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

import java.io.PrintStream;
import java.security.Permission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class MainTestCase
{
   @Test
   public void testCoverage()
   {
      new Decrypt();
   }

   private void testExitWithError(String args[]) throws Exception
   {
      SecurityManager oldManager = System.getSecurityManager();
      PrintStream oldErr = System.err;
      try
      {
         SecurityManager manager = new SecurityManager() {
            @Override
            public void checkExit(int status)
            {
               throw new SecurityException("exit " + status);
            }

            @Override
            public void checkPermission(Permission perm)
            {
               // okay
            }
         };
         System.setSecurityManager(manager);

         PrintStream err = mock(PrintStream.class);
         System.setErr(err);

         try
         {
            Decrypt.main(args);
            fail("Expected SecurityException, but really this can't be reached (JVM will exits)");
         }
         catch(SecurityException e)
         {
            assertEquals("exit 1", e.getMessage());
         }
         verify(err, atLeastOnce()).println(anyString());
      }
      finally
      {
         System.setErr(oldErr);
         System.setSecurityManager(oldManager);
      }
   }

   @Test
   public void testMaster() throws Exception
   {
      String master = "{WR51wo2sI1QHlQM0MhI7AULqJXRZvtoppQsqdg74p08=}";

      String args[] = { master };

      PrintStream oldOut = System.out;
      try
      {
         PrintStream out = mock(PrintStream.class);
         System.setOut(out);
         Decrypt.main(args);
         verify(out).println("Doh!");
      }
      finally
      {
         System.setOut(oldOut);
      }
   }

   @Test
   public void testPassword() throws Exception
   {
      String master = "Doh!";
      String pwd = "{fB3b7bF6RKUHOTcOH790i8jm4C4fIBM4BZ5UvXSTODk=}";

      String args[] = { pwd, master };

      PrintStream oldOut = System.out;
      try
      {
         PrintStream out = mock(PrintStream.class);
         System.setOut(out);
         Decrypt.main(args);
         verify(out).println("Eeek!");
      }
      finally
      {
         System.setOut(oldOut);
      }
   }

   @Test
   public void testTooLittle() throws Exception
   {
      String args[] = { };

      testExitWithError(args);
   }

   @Test
   public void testTooMuch() throws Exception
   {
      String args[] = { "a", "b", "c" };

      testExitWithError(args);
   }
}
