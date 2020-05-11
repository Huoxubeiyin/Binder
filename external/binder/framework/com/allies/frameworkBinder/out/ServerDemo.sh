# Script to start "am" on the device, which has a very rudimentary
# shell.
#
base=/system
export CLASSPATH=$base/framework/shellmanager-server.jar
exec app_process $base/bin com.allies.frameworkBinder.server.ShellManagerServer "$@"