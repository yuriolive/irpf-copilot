# Makefile for AI Agent IRPF project
.PHONY: help test test-basic test-unit test-integration test-all test-fast test-standalone test-check install-deps clean

# Default target
help:
	@echo "🤖 AI Agent IRPF - Available Commands"
	@echo "=================================="
	@echo ""
	@echo "Testing:"
	@echo "  test             - Run basic tests (default)"
	@echo "  test-basic       - Run basic functionality tests"
	@echo "  test-unit        - Run unit tests only"
	@echo "  test-integration - Run integration tests only"
	@echo "  test-all         - Run all tests with coverage"
	@echo "  test-fast        - Run tests excluding slow ones"
	@echo "  test-standalone  - Run standalone test functions"
	@echo "  test-check       - Check environment setup"
	@echo ""
	@echo "Development:"
	@echo "  install-deps     - Install development dependencies"
	@echo "  clean           - Clean up test artifacts"
	@echo "  format          - Format code with black"
	@echo "  lint            - Run code linting"
	@echo ""
	@echo "Examples:"
	@echo "  make test                    # Run basic tests"
	@echo "  make test-all                # Run all tests with coverage"
	@echo "  python run_tests.py --help   # See more options"

# Test targets
test: test-basic

test-basic:
	@echo "🧪 Running basic tests..."
	python run_tests.py basic

test-unit:
	@echo "🧪 Running unit tests..."
	python run_tests.py unit

test-integration:
	@echo "🧪 Running integration tests..."
	python run_tests.py integration

test-all:
	@echo "🧪 Running all tests with coverage..."
	python run_tests.py all

test-fast:
	@echo "🧪 Running fast tests..."
	python run_tests.py fast

test-standalone:
	@echo "🧪 Running standalone tests..."
	python run_tests.py standalone

test-check:
	@echo "🔍 Checking environment..."
	python run_tests.py check

# Development targets
install-deps:
	@echo "📦 Installing dependencies..."
	uv sync --group dev

format:
	@echo "🎨 Formatting code..."
	python -m black agent/ tests/ *.py

lint:
	@echo "🔍 Running linter..."
	python -m flake8 agent/ tests/ *.py

clean:
	@echo "🧹 Cleaning up..."
	rm -rf .pytest_cache/
	rm -rf htmlcov/
	rm -rf .coverage
	rm -rf **/__pycache__/
	rm -rf **/*.pyc
	find . -name "*.pyc" -delete
	find . -name "__pycache__" -type d -exec rm -rf {} +

# Specific test examples
test-checksum:
	@echo "🧪 Testing checksum functionality..."
	python run_tests.py --test tests/test_basic.py::TestChecksumFunctions

test-dbk:
	@echo "🧪 Testing DBK parsing..."
	python run_tests.py --test tests/test_dbk_parsing.py

test-agent:
	@echo "🧪 Testing agent functionality..."
	python run_tests.py --test tests/test_agent.py

test-main:
	@echo "🧪 Testing main functionality..."
	python run_tests.py --test tests/test_main.py

# List all available tests
list-tests:
	@echo "📋 Listing all available tests..."
	python run_tests.py --list
