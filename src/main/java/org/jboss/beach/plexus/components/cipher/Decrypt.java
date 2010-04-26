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

import org.sonatype.plexus.components.cipher.DefaultPlexusCipher;

/**
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class Decrypt
{
   public static void main(String args[]) throws Exception
   {
      if(args.length < 1 || args.length > 2)
      {
         System.err.println("Usage: Decrypt <pwd> [<master>]");
         System.err.println("\n\tTo decrypt the master password itself specify it as <pwd> and do not specify <master>.");
         System.exit(1);
      }

      String master = "settings.security";
      if(args.length > 1)
         master = args[1];

      String pwd = args[0];

      DefaultPlexusCipher cipher = new DefaultPlexusCipher();

      String result = cipher.decryptDecorated(pwd, master);

      System.out.println(result);
   }
}
