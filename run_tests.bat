@echo off
REM Windows batch script for running tests on AI Agent IRPF project
REM Usage: run_tests.bat [command]

setlocal enabledelayedexpansion

if "%1"=="" (
    set COMMAND=basic
) else (
    set COMMAND=%1
)

echo 🤖 AI Agent IRPF - Test Runner (Windows)
echo ==================================================

if "%COMMAND%"=="help" goto :help
if "%COMMAND%"=="basic" goto :basic
if "%COMMAND%"=="unit" goto :unit
if "%COMMAND%"=="integration" goto :integration
if "%COMMAND%"=="all" goto :all
if "%COMMAND%"=="fast" goto :fast
if "%COMMAND%"=="standalone" goto :standalone
if "%COMMAND%"=="check" goto :check
if "%COMMAND%"=="clean" goto :clean

:help
echo.
echo Available commands:
echo   basic       - Run basic functionality tests (default)
echo   unit        - Run unit tests only
echo   integration - Run integration tests only
echo   all         - Run all tests with coverage
echo   fast        - Run tests excluding slow ones
echo   standalone  - Run standalone test functions
echo   check       - Check environment setup
echo   clean       - Clean up test artifacts
echo   help        - Show this help message
echo.
echo Windows CMD Examples:
echo   run_tests.bat basic
echo   run_tests.bat all
echo   python run_tests.py --test tests\test_basic.py
echo   python run_tests.py check
goto :end

:basic
echo 🧪 Running basic tests...
python run_tests.py basic
goto :end

:unit
echo 🧪 Running unit tests...
python run_tests.py unit
goto :end

:integration
echo 🧪 Running integration tests...
python run_tests.py integration
goto :end

:all
echo 🧪 Running all tests with coverage...
python run_tests.py all
goto :end

:fast
echo 🧪 Running fast tests...
python run_tests.py fast
goto :end

:standalone
echo 🧪 Running standalone tests...
python run_tests.py standalone
goto :end

:check
echo 🔍 Checking environment...
python run_tests.py check
goto :end

:clean
echo 🧹 Cleaning up test artifacts...
if exist .pytest_cache rmdir /s /q .pytest_cache
if exist htmlcov rmdir /s /q htmlcov
if exist .coverage del .coverage
if exist coverage.xml del coverage.xml
for /d /r . %%d in (__pycache__) do @if exist "%%d" rmdir /s /q "%%d"
del /s /q *.pyc >nul 2>&1
echo Clean completed!
goto :end

:end
endlocal
