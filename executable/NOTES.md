## Executable jar from kotlin project
Use the Maven assembly plugin and run `mvn clean install` to make a executable jar with dependencies

## Open bat files with Windows terminal when double-clicking
Open `Registry Editor` as administrator by searching for it at the `Start` button

Then change
`HKEY_CLASSES_ROOT\batfile\shell\open\command` default value from:
````
"%1" %*
````
to:
````
"C:\Users\<user>\AppData\Local\Microsoft\WindowsApps\wt.exe" -p "Command Prompt" "%1"
````
See [How can I get batch files to run through the new Windows Terminal?](https://stackoverflow.com/questions/56191386/how-can-i-get-batch-files-to-run-through-the-new-windows-terminal)
